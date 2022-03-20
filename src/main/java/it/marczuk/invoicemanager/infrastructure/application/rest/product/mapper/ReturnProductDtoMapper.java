package it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper;

import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.ReturnProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReturnProductDtoMapper {

    private ReturnProductDtoMapper() {
    }

    public static List<ReturnProductDto> mapToReturnProductDto(List<Product> allProducts) {
        return allProducts
                .stream()
                .map(ReturnProductDtoMapper::mapToReturnProductDtoFunction)
                .collect(Collectors.toList());
    }

    public static ReturnProductDto mapToReturnProductDto(Product product) {
        return mapToReturnProductDtoFunction(product);
    }

    private static ReturnProductDto mapToReturnProductDtoFunction(Product product) {
        return ReturnProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .country(product.getCountry())
                .count(product.getCount())
                .netPrice(product.getNetPrice())
                .netValue(product.getNetValue())
                .taxValue(product.getTaxValue())
                .taxSum(product.getTaxSum())
                .grossValue(product.getGrossValue())
                .build();
    }
}
