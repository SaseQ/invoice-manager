package it.marczuk.invoicemanager.infrastructure.application.rest.invoice;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import it.marczuk.invoicemanager.domain.invoice.adapter.InvoiceServiceAdapter;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceServicePort;
import it.marczuk.invoicemanager.domain.invoice.service.InvoiceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvoiceConfig {

    @Bean
    public InvoiceServicePort invoiceServicePort(InvoiceRepositoryPort invoiceRepositoryPort, EmailNotificationPort emailNotificationPort) {
        return new InvoiceServiceAdapter(new InvoiceService(invoiceRepositoryPort, emailNotificationPort));
    }

}
