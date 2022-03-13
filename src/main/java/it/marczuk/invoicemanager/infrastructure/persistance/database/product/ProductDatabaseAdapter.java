package it.marczuk.invoicemanager.infrastructure.persistance.database.product;

import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity.ProductEntity;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity.ProductEntityMapper;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductDatabaseAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return ProductEntityMapper.mapToProduct(productRepository.findAll());
    }

    @Override
    public Product save(Product product) {
        ProductEntity result = productRepository.save(ProductEntityMapper.mapToProductEntity(product));
        return ProductEntityMapper.mapToProduct(result);
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        List<ProductEntity> result = productRepository.saveAll(ProductEntityMapper.mapToProductEntity(products));
        return ProductEntityMapper.mapToProduct(result);
    }
}
