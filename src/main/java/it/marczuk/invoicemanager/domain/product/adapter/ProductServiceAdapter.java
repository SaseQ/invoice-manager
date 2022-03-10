package it.marczuk.invoicemanager.domain.product.adapter;

import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import it.marczuk.invoicemanager.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductServiceAdapter implements ProductServicePort {

    private final ProductService productService;

    @Override
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @Override
    public Product addProduct(Product product) {
        return productService.addProduct(product);
    }
}
