package it.marczuk.invoicemanager.domain.invoice.service;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyServicePort;
import it.marczuk.invoicemanager.domain.company.service.TestCompanyData;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.model.PayType;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import it.marczuk.invoicemanager.domain.product.service.TestProductData;
import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.ReturnInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper.ReturnProductDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InvoiceServiceTest {

    @Mock
    private InvoiceRepositoryPort invoiceRepositoryPort;
    @Mock
    private CompanyServicePort companyServicePort;
    @Mock
    private ProductServicePort productServicePort;

    private InvoiceService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new InvoiceService(invoiceRepositoryPort, companyServicePort, productServicePort);
    }

    @Test
    void shouldGetInvoices() {
        // given
        List<Invoice> invoiceList = List.of(TestInvoiceData.getTestInvoice(1L));
        when(invoiceRepositoryPort.findAll())
                .thenReturn(invoiceList);

        // when
        List<ReturnInvoiceDto> actual = underTest.getInvoices();

        // then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldGetInvoiceById() {
        // given
        Invoice invoice = TestInvoiceData.getTestInvoice(1L);
        List<Product> productList = List.of(TestProductData.getTestProduct(1L));
        when(invoiceRepositoryPort.findById(invoice.getId()))
                .thenReturn(Optional.of(invoice));
        when(productServicePort.getProductsByInvoice(invoice))
                .thenReturn(productList);

        // when
        ReturnInvoiceDto actual = underTest.getInvoiceById(invoice.getId());

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getPlaceOfIssue()).isEqualTo("placeOfIssue");
        assertThat(actual.getDateOfIssue()).isEqualTo(LocalDate.of(2021, 3, 6));
        assertThat(actual.getDatePerformanceOfService()).isEqualTo(LocalDate.of(2021, 3, 7));
        assertThat(actual.getSeller()).isEqualTo(TestCompanyData.getTestCompany(1L));
        assertThat(actual.getBuyer()).isEqualTo(TestCompanyData.getTestCompany(2L));
        assertThat(actual.getProducts()).isEqualTo(ReturnProductDtoMapper.mapToReturnProductDto(productList));
        assertThat(actual.getPayType()).isEqualTo(PayType.BANK_TRANSFER);
        assertThat(actual.getPaymentDeadline()).isEqualTo(LocalDate.of(2021, 3, 8));
        assertThat(actual.getSumToPay()).isEqualTo(BigDecimal.valueOf(59.99));
        assertThat(actual.getSumToPayAsWords()).isEqualTo("Pisiąt pinć złotych");
    }

    @Test
    void shouldReturn404fromGetInvoiceById() {
        // given
        Invoice invoice = TestInvoiceData.getTestInvoice(1L);
        when(invoiceRepositoryPort.findById(invoice.getId()))
                .thenReturn(Optional.of(invoice));

        // then
        assertThatThrownBy(() -> underTest.getInvoiceById(2L))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessage("Could not find invoice by id: 2");
    }

    @Test
    void shouldAddInvoice() {
        // given
        Invoice invoice = TestInvoiceData.getEmptyTestInvoice();
        Company seller = TestCompanyData.getTestCompany(1L);
        Company buyer = TestCompanyData.getTestCompany(2L);
        List<Product> productList = List.of(TestProductData.getTestProduct(1L));
        when(companyServicePort.addCompany(seller))
                .thenReturn(seller);
        when(companyServicePort.addCompany(buyer))
                .thenReturn(buyer);
        when(invoiceRepositoryPort.save(invoice))
                .thenAnswer(invocationOnMock -> {
                    invoice.setId(1L);
                    invoice.setSeller(seller);
                    invoice.setBuyer(buyer);
                    return invoice;
                });
        when(productServicePort.addProducts(productList))
                .thenReturn(productList);

        // when
        ReturnInvoiceDto actual = underTest.addInvoice(invoice, productList);

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getPlaceOfIssue()).isEqualTo("placeOfIssue");
        assertThat(actual.getDateOfIssue()).isEqualTo(LocalDate.of(2021, 3, 6));
        assertThat(actual.getDatePerformanceOfService()).isEqualTo(LocalDate.of(2021, 3, 7));
        assertThat(actual.getSeller()).isEqualTo(TestCompanyData.getTestCompany(1L));
        assertThat(actual.getBuyer()).isEqualTo(TestCompanyData.getTestCompany(2L));
        assertThat(actual.getProducts()).isEqualTo(ReturnProductDtoMapper.mapToReturnProductDto(productList));
        assertThat(actual.getPayType()).isEqualTo(PayType.BANK_TRANSFER);
        assertThat(actual.getPaymentDeadline()).isEqualTo(LocalDate.of(2021, 3, 8));
        assertThat(actual.getSumToPay()).isEqualTo(BigDecimal.valueOf(12.3).setScale(2, RoundingMode.HALF_EVEN));
        assertThat(actual.getSumToPayAsWords()).isEqualTo("dwanaście PLN 30/100");
    }

    @Test
    void shouldEditInvoice() {
        // given
        Invoice invoice = TestInvoiceData.getTestInvoice(1L);
        when(invoiceRepositoryPort.findById(invoice.getId()))
                .thenReturn(Optional.of(invoice));
        when(invoiceRepositoryPort.save(invoice))
                .thenAnswer(invocationOnMock -> {
                    invoice.setPlaceOfIssue("editedPlaceOfIssue");
                    invoice.setDateOfIssue(LocalDate.of(2021, 3, 7));
                    invoice.setDatePerformanceOfService(LocalDate.of(2021, 3, 8));
                    invoice.setPayType(PayType.CASH);
                    invoice.setPaymentDeadline(LocalDate.of(2021, 3, 9));
                    return invoice;
                });

        // when
        ReturnInvoiceDto actual = underTest.editInvoice(invoice);

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getPlaceOfIssue()).isEqualTo("editedPlaceOfIssue");
        assertThat(actual.getDateOfIssue()).isEqualTo(LocalDate.of(2021, 3, 7));
        assertThat(actual.getDatePerformanceOfService()).isEqualTo(LocalDate.of(2021, 3, 8));
        assertThat(actual.getPayType()).isEqualTo(PayType.CASH);
        assertThat(actual.getPaymentDeadline()).isEqualTo(LocalDate.of(2021, 3, 9));
    }

    @Test
    void shouldDeleteInvoice() {
        // given
        Invoice invoice = TestInvoiceData.getTestInvoice(1L);
        List<Product> productList = List.of(TestProductData.getTestProduct(1L));
        when(invoiceRepositoryPort.findById(invoice.getId()))
                .thenReturn(Optional.of(invoice));
        when(productServicePort.getProductsByInvoice(invoice))
                .thenReturn(productList);

        // when
        underTest.deleteInvoice(invoice.getId());

        // then
        verify(invoiceRepositoryPort).delete(invoice);
        productList.forEach(product -> verify(productServicePort).deleteProduct(product.getId()));
    }
}
