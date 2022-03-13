package it.marczuk.invoicemanager.domain.product.port;

import it.marczuk.invoicemanager.domain.product.model.Product;

import java.util.List;

public interface ProductRepositoryPort {

    List<Product> findAll();

    Product save(Product product);

    List<Product> saveAll(List<Product> products);

}
