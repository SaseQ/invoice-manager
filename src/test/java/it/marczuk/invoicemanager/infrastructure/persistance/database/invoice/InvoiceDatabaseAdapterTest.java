package it.marczuk.invoicemanager.infrastructure.persistance.database.invoice;

import it.marczuk.invoicemanager.domain.company.service.TestCompanyData;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.model.PayType;
import it.marczuk.invoicemanager.infrastructure.persistance.database.config.TestConfig;
import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.repository.InvoiceRepository;
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
class InvoiceDatabaseAdapterTest {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("test");

    @Autowired
    private InvoiceRepository invoiceRepository;

    private InvoiceDatabaseAdapter invoiceDatabaseAdapter;

    @BeforeEach
    void setUp() {
        invoiceDatabaseAdapter = new InvoiceDatabaseAdapter(invoiceRepository);
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.liquibase.change-log", () -> "db/changelog/test-dbchangelog.yaml");
    }

    @Test
    void shouldReturnAllInvoices() {
        // when
        List<Invoice> actual = invoiceDatabaseAdapter.findAll();

        // then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldReturnInvoiceById() {
        // when
        Optional<Invoice> actual = invoiceDatabaseAdapter.findById(1L);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get().getId()).isEqualTo(1L);
        assertThat(actual.get().getPlaceOfIssue()).isEqualTo("testPlaceOfIssue");
        assertThat(actual.get().getDateOfIssue()).isEqualTo(LocalDate.of(2003, 3, 3));
        assertThat(actual.get().getDatePerformanceOfService()).isEqualTo(LocalDate.of(2003, 3, 3));
        assertThat(actual.get().getPayType()).isEqualTo(PayType.CASH);
        assertThat(actual.get().getPaymentDeadline()).isEqualTo(LocalDate.of(2003, 3, 3));
        assertThat(actual.get().getSumToPay()).isEqualTo(BigDecimal.valueOf(59.99));
        assertThat(actual.get().getSumToPayAsWords()).isEqualTo("testSumToPayAsWords");
    }

    @Test
    void shouldSaveNewInvoice() {
        // give
        Invoice invoice = new Invoice(
                null,
                "placeOfIssue",
                LocalDate.of(2021, 3, 6),
                LocalDate.of(2021, 3, 7),
                TestCompanyData.getTestCompany(3L),
                TestCompanyData.getTestCompany(4L),
                PayType.BANK_TRANSFER,
                LocalDate.of(2021, 3, 8),
                BigDecimal.valueOf(59.99),
                "Pisiąt pinć złotych"
        );

        // when
        Invoice actual = invoiceDatabaseAdapter.save(invoice);

        // then
        assertThat(actual.getId()).isEqualTo(3L);
        assertThat(actual.getPlaceOfIssue()).isEqualTo("placeOfIssue");
        assertThat(actual.getDateOfIssue()).isEqualTo(LocalDate.of(2021, 3, 6));
        assertThat(actual.getDatePerformanceOfService()).isEqualTo(LocalDate.of(2021, 3, 7));
        assertThat(actual.getSeller().getId()).isEqualTo(3L);
        assertThat(actual.getBuyer().getId()).isEqualTo(4L);
        assertThat(actual.getPayType()).isEqualTo(PayType.BANK_TRANSFER);
        assertThat(actual.getPaymentDeadline()).isEqualTo(LocalDate.of(2021, 3, 8));
        assertThat(actual.getSumToPay()).isEqualTo(BigDecimal.valueOf(59.99));
        assertThat(actual.getSumToPayAsWords()).isEqualTo("Pisiąt pinć złotych");
    }

    @Test
    void shouldDeleteInvoice() {
        // given
        Optional<Invoice> invoiceOptional = invoiceDatabaseAdapter.findById(2L);

        if(invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();

            //when
            invoiceDatabaseAdapter.delete(invoice);
        }

        //then
        Optional<Invoice> actual = invoiceDatabaseAdapter.findById(2L);
        assertThat(actual).isEmpty();
    }
}