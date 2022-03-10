package it.marczuk.invoicemanager.infrastructure.application.config.address.database;

import it.marczuk.invoicemanager.domain.address.port.AddressRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.persistance.database.address.AddressDatabaseAdapter;
import it.marczuk.invoicemanager.infrastructure.persistance.database.address.repository.AddressRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressDatabaseConfig {

    @Bean
    public AddressRepositoryPort addressRepositoryPort(AddressRepository addressRepository) {
        return new AddressDatabaseAdapter(addressRepository);
    }
}
