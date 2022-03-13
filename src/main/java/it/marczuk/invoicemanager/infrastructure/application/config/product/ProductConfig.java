package it.marczuk.invoicemanager.infrastructure.application.config.product;

import it.marczuk.invoicemanager.domain.common.taxclient.TaxClientPort;
import it.marczuk.invoicemanager.domain.product.adapter.ProductServiceAdapter;
import it.marczuk.invoicemanager.domain.product.port.ProductRepositoryPort;
import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import it.marczuk.invoicemanager.domain.product.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductServicePort productServicePort(ProductRepositoryPort productRepositoryPort, TaxClientPort taxClientPort) {
        return new ProductServiceAdapter(new ProductService(productRepositoryPort, taxClientPort));
    }
}
