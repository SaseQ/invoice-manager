package it.marczuk.invoicemanager.domain.product.service;

import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositoryPort productRepositoryPort;


    public List<Product> getProducts() {
        return productRepositoryPort.findAll();
    }

    public Product addProduct(Product product) {
        return productRepositoryPort.save(product);
    }
}
