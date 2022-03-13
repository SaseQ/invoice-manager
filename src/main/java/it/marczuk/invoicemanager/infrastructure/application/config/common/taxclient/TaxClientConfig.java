package it.marczuk.invoicemanager.infrastructure.application.config.common.taxclient;

import it.marczuk.invoicemanager.domain.common.taxclient.TaxClientPort;
import it.marczuk.invoicemanager.infrastructure.webclient.tax.TaxClientAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TaxClientConfig {

    @Bean
    public TaxClientPort taxClientPort(RestTemplate restTemplate) {
        return new TaxClientAdapter(restTemplate);
    }
}
