package it.marczuk.invoicemanager.infrastructure.application.config.invoice;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import it.marczuk.invoicemanager.domain.company.port.CompanyServicePort;
import it.marczuk.invoicemanager.domain.invoice.adapter.InvoiceServiceAdapter;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceServicePort;
import it.marczuk.invoicemanager.domain.invoice.service.InvoiceService;
import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvoiceConfig {

    @Bean
    public InvoiceServicePort invoiceServicePort(InvoiceRepositoryPort invoiceRepositoryPort,
                                                 CompanyServicePort companyServicePort,
                                                 ProductServicePort productServicePort,
                                                 EmailNotificationPort emailNotificationPort) {
        return new InvoiceServiceAdapter(new InvoiceService(invoiceRepositoryPort,
                companyServicePort,
                productServicePort,
                emailNotificationPort));
    }

}
