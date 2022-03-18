package it.marczuk.invoicemanager.domain.product.adapter;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import it.marczuk.invoicemanager.domain.product.service.ProductService;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.ReturnProductDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductServiceAdapter implements ProductServicePort {

    private final ProductService productService;

    @Override
    public List<ReturnProductDto> getProducts() {
        return productService.getProducts();
    }

    @Override
    public ReturnProductDto getProductById(Long id) {
        return productService.getProductById(id);
    }

    @Override
    public List<Product> getProductsByInvoice(Invoice invoice) {
        return productService.getProductByInvoice(invoice);
    }

    @Override
    public ReturnProductDto addProduct(Product product) {
        return productService.addProduct(product);
    }

    @Override
    public List<Product> addProducts(List<Product> products) {
        return productService.addProducts(products);
    }

    @Override
    public ReturnProductDto editProduct(Product product) {
        return productService.editProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }
}
