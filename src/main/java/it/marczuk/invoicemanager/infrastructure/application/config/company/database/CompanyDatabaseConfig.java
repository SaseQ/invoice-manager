package it.marczuk.invoicemanager.infrastructure.application.config.company.database;

import it.marczuk.invoicemanager.domain.company.port.CompanyRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.persistance.database.company.CompanyDatabaseAdapter;
import it.marczuk.invoicemanager.infrastructure.persistance.database.company.repository.CompanyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanyDatabaseConfig {

    @Bean
    public CompanyRepositoryPort companyRepositoryPort(CompanyRepository companyRepository) {
        return new CompanyDatabaseAdapter(companyRepository);
    }
}
