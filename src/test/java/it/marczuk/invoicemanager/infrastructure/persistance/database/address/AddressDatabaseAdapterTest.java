package it.marczuk.invoicemanager.infrastructure.persistance.database.address;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.infrastructure.persistance.database.address.repository.AddressRepository;
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
class AddressDatabaseAdapterTest {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("test");

    @Autowired
    private AddressRepository addressRepository;

    private AddressDatabaseAdapter addressDatabaseAdapter;

    @BeforeEach
    void setUp() {
        addressDatabaseAdapter = new AddressDatabaseAdapter(addressRepository);
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.liquibase.change-log", () -> "db/changelog/test-dbchangelog.yaml");
    }

    @Test
    void shouldReturnAllAddresses() {
        //when
        List<Address> actual = addressDatabaseAdapter.findAll();

        //then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldReturnAddressById() {
        //when
        Optional<Address> actual = addressDatabaseAdapter.findById(1L);

        //then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get().getId()).isEqualTo(1L);
        assertThat(actual.get().getStreetName()).isEqualTo("testStreetName");
        assertThat(actual.get().getHouseNumber()).isEqualTo("11");
        assertThat(actual.get().getZipCode()).isEqualTo("00-000");
        assertThat(actual.get().getCity()).isEqualTo("testCity");
        assertThat(actual.get().getCountry()).isEqualTo(CountryCode.PL);
    }

    @Test
    void shouldAddNewAddress() {
        // given
        Address address = new Address(
                null,
                "testStreetName2",
                "22",
                "00-001",
                "testCity2",
                CountryCode.PL
        );

        //when
        Address actual = addressDatabaseAdapter.save(address);

        //then
        assertThat(actual.getId()).isEqualTo(2L);
        assertThat(actual.getStreetName()).isEqualTo("testStreetName2");
        assertThat(actual.getHouseNumber()).isEqualTo("22");
        assertThat(actual.getZipCode()).isEqualTo("00-001");
        assertThat(actual.getCity()).isEqualTo("testCity2");
        assertThat(actual.getCountry()).isEqualTo(CountryCode.PL);
    }

    @Test
    void shouldDeleteAddress() {
        // given
        Optional<Address> addressOptional = addressDatabaseAdapter.findById(1L);

        if(addressOptional.isPresent()) {
            Address address = addressOptional.get();

            //when
            addressDatabaseAdapter.delete(address);
        }

        //then
        Optional<Address> actual = addressDatabaseAdapter.findById(1L);
        assertThat(actual).isEmpty();
    }

}