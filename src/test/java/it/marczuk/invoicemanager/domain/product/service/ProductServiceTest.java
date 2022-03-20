package it.marczuk.invoicemanager.domain.product.service;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.common.taxclient.TaxClientPort;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.ReturnProductDto;
import it.marczuk.invoicemanager.infrastructure.webclient.tax.dto.TaxDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;
    @Mock
    private TaxClientPort taxClientPort;

    private ProductService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ProductService(productRepositoryPort, taxClientPort);
    }

    @Test
    void shouldGetProducts() {
        // given
        List<Product> productList = List.of(TestProductData.getTestProduct(1L));
        when(productRepositoryPort.findAll())
                .thenReturn(productList);

        // when
        List<ReturnProductDto> actual = underTest.getProducts();

        // then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldGetProductById() {
        // given
        Product product = TestProductData.getTestProduct(1L);
        when(productRepositoryPort.findById(product.getId()))
                .thenReturn(Optional.of(product));

        // when
        ReturnProductDto actual = underTest.getProductById(product.getId());

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getName()).isEqualTo("product");
        assertThat(actual.getCountry()).isEqualTo(CountryCode.PL);
        assertThat(actual.getCount()).isEqualTo(2);
        assertThat(actual.getNetPrice()).isEqualTo(BigDecimal.valueOf(5));
        assertThat(actual.getNetValue()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(actual.getTaxValue()).isEqualTo(23);
        assertThat(actual.getTaxSum()).isEqualTo(BigDecimal.valueOf(2.3));
        assertThat(actual.getGrossValue()).isEqualTo(BigDecimal.valueOf(12.3));
    }

    @Test
    void shouldReturn404fromGetProductById() {
        // given
        Product product = TestProductData.getTestProduct(1L);
        when(productRepositoryPort.findById(product.getId()))
                .thenReturn(Optional.of(product));

        // then
        assertThatThrownBy(() -> underTest.getProductById(2L))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessage("Could not find product by id: 2");
    }

    @Test
    void shouldGetProductByInvoice() {
        // given
        Product product = TestProductData.getTestProduct(1L);
        List<Product> productList = List.of(product);
        Invoice invoice = product.getInvoice();
        when(productRepositoryPort.findByInvoice(invoice))
                .thenReturn(productList);

        // when
        List<Product> actual = underTest.getProductByInvoice(invoice);

        // then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldAddProduct() {
        // given
        Product product = TestProductData.getEmptyTestProduct(CountryCode.PL);
        TaxDto taxDto = TestTaxDtoData.getTaxDtoData();
        when(productRepositoryPort.save(product))
                .thenAnswer(invocationOnMock -> {
                    product.setId(1L);
                    return product;
                });
        when(taxClientPort.getTax(product.getCountry().getAlpha2()))
                .thenReturn(taxDto);

        // when
        ReturnProductDto actual = underTest.addProduct(product);

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getName()).isEqualTo("product");
        assertThat(actual.getCountry()).isEqualTo(CountryCode.PL);
        assertThat(actual.getCount()).isEqualTo(2);
        assertThat(actual.getNetPrice()).isEqualTo(BigDecimal.valueOf(5));
        assertThat(actual.getNetValue()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(actual.getTaxValue()).isEqualTo(23);
        assertThat(actual.getTaxSum().doubleValue()).isEqualTo(2.3);
        assertThat(actual.getGrossValue().doubleValue()).isEqualTo(12.3);
    }

    @Test
    void shouldAddProducts() {
        // given
        List<Product> productList = List.of(TestProductData.getEmptyTestProduct(CountryCode.PL));
        TaxDto taxDto = TestTaxDtoData.getTaxDtoData();
        when(productRepositoryPort.saveAll(productList))
                .thenAnswer(invocationOnMock -> {
                    long id = 1L;
                    for(Product product : productList) {
                        product.setId(id);
                        id++;
                    }
                    return productList;
                });
        productList.forEach(product ->
            when(taxClientPort.getTax(product.getCountry().getAlpha2()))
                    .thenReturn(taxDto)
        );

        // when
        List<Product> actual = underTest.addProducts(productList);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void shouldEditProduct() {
        // given
        Product product = TestProductData.getTestProduct(1L);
        TaxDto taxDto = TestTaxDtoData.getEditTaxDtoData();
        when(productRepositoryPort.findById(product.getId()))
                .thenReturn(Optional.of(product));
        when(taxClientPort.getTax(product.getCountry().getAlpha2()))
                .thenReturn(taxDto);
        when(productRepositoryPort.save(product))
                .thenAnswer(invocationOnMock -> {
                    product.setName("editedProduct");
                    product.setCount(1);
                    product.setCountry(CountryCode.DE);
                    product.setNetPrice(BigDecimal.valueOf(10));
                    return product;
                });

        // when
        ReturnProductDto actual = underTest.editProduct(product);

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getName()).isEqualTo("editedProduct");
        assertThat(actual.getCountry()).isEqualTo(CountryCode.DE);
        assertThat(actual.getCount()).isEqualTo(1);
        assertThat(actual.getNetPrice()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(actual.getNetValue()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(actual.getTaxValue()).isEqualTo(19);
        assertThat(actual.getTaxSum().doubleValue()).isEqualTo(1.9);
        assertThat(actual.getGrossValue().doubleValue()).isEqualTo(11.9);
    }

    @Test
    void shouldDeleteProduct() {
        // given
        Product product = TestProductData.getTestProduct(1L);
        when(productRepositoryPort.findById(product.getId()))
                .thenReturn(Optional.of(product));

        // when
        underTest.deleteProduct(product.getId());

        // then
        verify(productRepositoryPort).delete(product);
    }
}