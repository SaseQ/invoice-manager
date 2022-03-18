package it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity;

import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.entity.InvoiceEntityMapper;

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

    public static ProductEntity mapToProductEntity(Product product) {
        return mapToProductEntityFunction(product);
    }

    public static List<Product> mapToProductWithoutInvoice(List<ProductEntity> allProductEntities) {
        return allProductEntities
                .stream()
                .map(ProductEntityMapper::mapToProductWithoutInvoiceFunction)
                .collect(Collectors.toList());
    }

    public static Product mapToProductWithoutInvoice(ProductEntity productEntity) {
        return mapToProductWithoutInvoiceFunction(productEntity);
    }

    public static ProductEntity mapToProductEntityWithoutInvoice(Product product) {
        return mapToProductEntityWithoutInvoiceFunction(product);
    }

    public static Optional<Product> mapToProductWithoutInvoice(Optional<ProductEntity> productEntityOptional) {
        return productEntityOptional
                .map(ProductEntityMapper::mapToProductWithoutInvoiceFunction);
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
                productEntity.getGrossValue(),
                InvoiceEntityMapper.mapToInvoice(productEntity.getInvoice())
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
                product.getGrossValue(),
                InvoiceEntityMapper.mapToInvoiceEntity(product.getInvoice())
        );
    }

    private static Product mapToProductWithoutInvoiceFunction(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getCount(),
                productEntity.getNetPrice(),
                productEntity.getNetValue(),
                productEntity.getTaxValue(),
                productEntity.getTaxSum(),
                productEntity.getGrossValue(),
                null
        );
    }

    private static ProductEntity mapToProductEntityWithoutInvoiceFunction(Product product) {
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
