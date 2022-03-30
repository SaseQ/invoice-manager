package it.marczuk.invoicemanager.infrastructure.persistance.database.product;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.company.service.TestCompanyData;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.model.PayType;
import it.marczuk.invoicemanager.domain.invoice.service.TestInvoiceData;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.persistance.database.config.TestConfig;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = TestConfig.class)
class ProductDatabaseAdapterTest {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("test");

    @Autowired
    private ProductRepository productRepository;

    private ProductDatabaseAdapter productDatabaseAdapter;

    @BeforeEach
    void setUp() {
        productDatabaseAdapter = new ProductDatabaseAdapter(productRepository);
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.liquibase.change-log", () -> "db/changelog/test-dbchangelog.yaml");
    }

    @Test
    void shouldReturnAllProducts() {
        // when
        List<Product> actual = productDatabaseAdapter.findAll();

        // then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldReturnProductById() {
        // when
        Optional<Product> actual = productDatabaseAdapter.findById(1L);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get().getId()).isEqualTo(1L);
        assertThat(actual.get().getName()).isEqualTo("testName");
        assertThat(actual.get().getCountry()).isEqualTo(CountryCode.PL);
        assertThat(actual.get().getCount()).isEqualTo(1);
        assertThat(actual.get().getNetPrice().intValue()).isEqualTo(100);
        assertThat(actual.get().getNetValue().intValue()).isEqualTo(100);
        assertThat(actual.get().getTaxValue()).isEqualTo(23);
        assertThat(actual.get().getTaxSum().intValue()).isEqualTo(23);
        assertThat(actual.get().getGrossValue().intValue()).isEqualTo(123);
    }

    @Test
    void shouldReturnProductByInvoice() {
        // given
        Invoice invoice = new Invoice(
                1L,
                "testPlaceOfIssue",
                LocalDate.of(2003, 3, 3),
                LocalDate.of(2003, 3, 3),
                TestCompanyData.getTestCompany(1L),
                TestCompanyData.getTestCompany(1L),
                PayType.CASH,
                LocalDate.of(2003, 3, 3),
                BigDecimal.valueOf(59.99),
                "testSumToPayAsWords"
        );

        // when
        List<Product> actual = productDatabaseAdapter.findByInvoice(invoice);

        //then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldSaveNewProduct() {
        // given
        Product product = new Product(
                null,
                "product",
                CountryCode.PL,
                2,
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(10),
                23,
                BigDecimal.valueOf(2.3),
                BigDecimal.valueOf(12.3),
                TestInvoiceData.getTestInvoice(1L)
        );

        //when
        Product actual = productDatabaseAdapter.save(product);

        //then
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
    void shouldSaveAllNewProducts() {
        // given
        List<Product> products = List.of(new Product(
                null,
                "product",
                CountryCode.PL,
                2,
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(10),
                23,
                BigDecimal.valueOf(2.3),
                BigDecimal.valueOf(12.3),
                TestInvoiceData.getTestInvoice(2L)
        ));

        //when
        List<Product> actual = productDatabaseAdapter.saveAll(products);

        //then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldDeleteProduct() {
        // given
        Optional<Product> productOptional = productDatabaseAdapter.findById(2L);

        if(productOptional.isPresent()) {
            Product product = productOptional.get();

            //when
            productDatabaseAdapter.delete(product);
        }

        //then
        Optional<Product> actual = productDatabaseAdapter.findById(2L);
        assertThat(actual).isEmpty();
    }
}