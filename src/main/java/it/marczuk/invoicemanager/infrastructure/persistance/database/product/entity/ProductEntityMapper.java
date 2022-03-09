package it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity;

import it.marczuk.invoicemanager.domain.product.model.Product;

public class ProductEntityMapper {

    private ProductEntityMapper() {
    }

    public static Product mapToProduct(ProductEntity productEntity) {
        return mapToProductFunction(productEntity);
    }

    public static ProductEntity mapToProductEntity(Product product) {
        return mapToProductEntityFunction(product);
    }

    private static Product mapToProductFunction(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getCount(),
                productEntity.getNetPrice(),
                productEntity.getVatValue(),
                productEntity.getVatSum(),
                productEntity.getGrossPrice()
        );
    }

    private static ProductEntity mapToProductEntityFunction(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getCount(),
                product.getNetPrice(),
                product.getVatValue(),
                product.getVatSum(),
                product.getGrossPrice()
        );
    }
}
