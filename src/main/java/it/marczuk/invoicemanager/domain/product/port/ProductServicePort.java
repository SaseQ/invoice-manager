package it.marczuk.invoicemanager.domain.product.port;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.ReturnProductDto;

import java.util.List;

public interface ProductServicePort {

    List<ReturnProductDto> getProducts();

    ReturnProductDto getProductById(Long id);

    List<Product> getProductsByInvoice(Invoice invoice);

    ReturnProductDto addProduct(Product product);

    List<Product> addProducts(List<Product> products);

    ReturnProductDto editProduct(Product product);

    void deleteProduct(Long id);
}
