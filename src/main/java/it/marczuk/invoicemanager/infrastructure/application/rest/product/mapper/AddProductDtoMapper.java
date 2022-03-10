package it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper;

import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.AddProductDto;

public class AddProductDtoMapper {

    private static final Long EMPTY_ID = null;

    private AddProductDtoMapper() {
    }

    public static Product mapToProduct(AddProductDto addProductDto) {
        return mapToProductFunction(addProductDto);
    }

    private static Product mapToProductFunction(AddProductDto addProductDto) {
        return new Product(
                EMPTY_ID,
                addProductDto.getName(),
                addProductDto.getCount(),
                addProductDto.getNetPrice(),
                addProductDto.getVatValue(),
                addProductDto.getVatSum(),
                addProductDto.getGrossPrice()
        );
    }
}
