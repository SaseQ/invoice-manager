package it.marczuk.invoicemanager.infrastructure.persistance.database.company;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.service.TestAddressData;
import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.infrastructure.persistance.database.company.repository.CompanyRepository;
import it.marczuk.invoicemanager.infrastructure.persistance.database.config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = TestConfig.class)
class CompanyDatabaseAdapterTest {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("test");

    @Autowired
    private CompanyRepository companyRepository;

    private CompanyDatabaseAdapter companyDatabaseAdapter;

    @BeforeEach
    void setUp() {
        companyDatabaseAdapter = new CompanyDatabaseAdapter(companyRepository);
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.liquibase.change-log", () -> "db/changelog/test-dbchangelog.yaml");
    }

    @Test
    void shouldReturnAllCompanies() {
        // when
        List<Company> actual = companyDatabaseAdapter.findAll();

        // then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldReturnCompanyById() {
        // when
        Optional<Company> actual = companyDatabaseAdapter.findById(1L);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get().getId()).isEqualTo(1L);
        assertThat(actual.get().getCompanyName()).isEqualTo("testCompanyName");
        assertThat(actual.get().getCompanyOwnerFirstName()).isEqualTo("testFirstName");
        assertThat(actual.get().getCompanyOwnerSecondName()).isEqualTo("testSecondName");
        assertThat(actual.get().getTaxIdentificationNumber()).isEqualTo("1234567890");
        assertThat(actual.get().getAddress().getId()).isEqualTo(1L);
    }

    @Test
    void shouldSaveNewCompany() {
        // given
        Company company = new Company(
                null,
                "testCompanyName2",
                "testFirstName2",
                "testSecondName2",
                "0123456789",
                TestAddressData.getTestAddress(3L)
        );

        //when
        Company actual = companyDatabaseAdapter.save(company);

        //then
        assertThat(actual.getId()).isEqualTo(3L);
        assertThat(actual.getCompanyName()).isEqualTo("testCompanyName2");
        assertThat(actual.getCompanyOwnerFirstName()).isEqualTo("testFirstName2");
        assertThat(actual.getCompanyOwnerSecondName()).isEqualTo("testSecondName2");
        assertThat(actual.getTaxIdentificationNumber()).isEqualTo("0123456789");
        assertThat(actual.getAddress().getId()).isEqualTo(3L);
    }

    @Test
    void shouldDeleteCompany() {
        // given
        Optional<Company> companyOptional = companyDatabaseAdapter.findById(2L);

        if(companyOptional.isPresent()) {
            Company company = companyOptional.get();

            //when
            companyDatabaseAdapter.delete(company);
        }

        //then
        Optional<Company> actual = companyDatabaseAdapter.findById(2L);
        assertThat(actual).isEmpty();
    }
}