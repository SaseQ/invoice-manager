package it.marczuk.invoicemanager.infrastructure.application.config.invoice.database;

import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.InvoiceDatabaseAdapter;
import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.repository.InvoiceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvoiceDatabaseConfig {

    @Bean
    public InvoiceRepositoryPort invoiceRepositoryPort(InvoiceRepository invoiceRepository) {
        return new InvoiceDatabaseAdapter(invoiceRepository);
    }
}
