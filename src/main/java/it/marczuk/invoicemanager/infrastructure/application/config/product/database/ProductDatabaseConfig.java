package it.marczuk.invoicemanager.infrastructure.application.config.product.database;

import it.marczuk.invoicemanager.domain.product.port.ProductRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.ProductDatabaseAdapter;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDatabaseConfig {

    @Bean
    public ProductRepositoryPort productRepositoryPort(ProductRepository productRepository) {
        return new ProductDatabaseAdapter(productRepository);
    }
}
