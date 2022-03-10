package it.marczuk.invoicemanager.infrastructure.application.config.address;

import it.marczuk.invoicemanager.domain.address.adapter.AddressServiceAdapter;
import it.marczuk.invoicemanager.domain.address.port.AddressRepositoryPort;
import it.marczuk.invoicemanager.domain.address.port.AddressServicePort;
import it.marczuk.invoicemanager.domain.address.service.AddressService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfig {

    @Bean
    public AddressServicePort addressServicePort(AddressRepositoryPort addressRepositoryPort) {
        return new AddressServiceAdapter(new AddressService(addressRepositoryPort));
    }
}
