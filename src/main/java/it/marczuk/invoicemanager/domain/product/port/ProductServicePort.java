package it.marczuk.invoicemanager.domain.product.port;

import it.marczuk.invoicemanager.domain.product.model.Product;

import java.util.List;

public interface ProductServicePort {

    List<Product> getProducts();

    Product getProductById(Long id);

    Product addProduct(Product product);

    List<Product> addProducts(List<Product> products);
}
