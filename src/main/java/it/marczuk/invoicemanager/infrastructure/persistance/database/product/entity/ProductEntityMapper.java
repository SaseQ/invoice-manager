package it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity;

import it.marczuk.invoicemanager.domain.product.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductEntityMapper {

    private ProductEntityMapper() {
    }

    public static List<Product> mapToProduct(List<ProductEntity> allProductEntities) {
        return allProductEntities
                .stream()
                .map(ProductEntityMapper::mapToProductFunction)
                .collect(Collectors.toList());
    }

    public static List<ProductEntity> mapToProductEntity(List<Product> allProducts) {
        return allProducts
                .stream()
                .map(ProductEntityMapper::mapToProductEntityFunction)
                .collect(Collectors.toList());
    }

    public static Product mapToProduct(ProductEntity productEntity) {
        return mapToProductFunction(productEntity);
    }

    public static ProductEntity mapToProductEntity(Product product) {
        return mapToProductEntityFunction(product);
    }

    public static Optional<Product> mapToProduct(Optional<ProductEntity> productEntityOptional) {
        return productEntityOptional
                .map(ProductEntityMapper::mapToProductFunction);
    }

    private static Product mapToProductFunction(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getCount(),
                productEntity.getNetPrice(),
                productEntity.getNetValue(),
                productEntity.getTaxValue(),
                productEntity.getTaxSum(),
                productEntity.getGrossValue()
        );
    }

    private static ProductEntity mapToProductEntityFunction(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getCount(),
                product.getNetPrice(),
                product.getNetValue(),
                product.getTaxValue(),
                product.getTaxSum(),
                product.getGrossValue()
        );
    }
}
