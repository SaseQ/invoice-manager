package it.marczuk.invoicemanager.infrastructure.application.config.company;

import it.marczuk.invoicemanager.domain.company.adapter.CompanyServiceAdapter;
import it.marczuk.invoicemanager.domain.company.port.CompanyRepositoryPort;
import it.marczuk.invoicemanager.domain.company.port.CompanyServicePort;
import it.marczuk.invoicemanager.domain.company.service.CompanyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanyConfig {

    @Bean
    public CompanyServicePort companyServicePort(CompanyRepositoryPort companyRepositoryPort) {
        return new CompanyServiceAdapter(new CompanyService(companyRepositoryPort));
    }
}
