package it.marczuk.invoicemanager.domain.product.port;

import it.marczuk.invoicemanager.domain.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    List<Product> saveAll(List<Product> products);

}
