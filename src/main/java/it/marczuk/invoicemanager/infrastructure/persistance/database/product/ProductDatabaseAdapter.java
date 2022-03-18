package it.marczuk.invoicemanager.infrastructure.persistance.database.product;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.entity.InvoiceEntityMapper;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity.ProductEntity;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity.ProductEntityMapper;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductDatabaseAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return ProductEntityMapper.mapToProductWithoutInvoice(productRepository.findAll());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return ProductEntityMapper.mapToProductWithoutInvoice(productRepository.findById(id));
    }

    @Override
    public List<Product> findByInvoice(Invoice invoice) {
        return ProductEntityMapper.mapToProduct(productRepository.findAllByInvoice(InvoiceEntityMapper.mapToInvoiceEntity(invoice)));
    }

    @Override
    public Product save(Product product) {
        ProductEntity result = productRepository.save(ProductEntityMapper.mapToProductEntityWithoutInvoice(product));
        return ProductEntityMapper.mapToProductWithoutInvoice(result);
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        List<ProductEntity> result = productRepository.saveAll(ProductEntityMapper.mapToProductEntity(products));
        return ProductEntityMapper.mapToProduct(result);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(ProductEntityMapper.mapToProductEntityWithoutInvoice(product));
    }
}
