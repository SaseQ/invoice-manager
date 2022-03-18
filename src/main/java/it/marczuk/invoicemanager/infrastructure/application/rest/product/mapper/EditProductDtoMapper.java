package it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.EditProductDto;

import java.math.BigDecimal;

public class EditProductDtoMapper {

    private static final BigDecimal EMPTY_NET_VALUE = null;
    private static final Integer EMPTY_VAT_VALUE = null;
    private static final BigDecimal EMPTY_VAT_SUM = null;
    private static final BigDecimal EMPTY_GROSS_VALUE = null;
    private static final Invoice EMPTY_INVOICE = null;

    private EditProductDtoMapper() {
    }

    public static Product mapToProduct(EditProductDto editProductDto) {
        return mapToProductFunction(editProductDto);
    }

    private static Product mapToProductFunction(EditProductDto editProductDto) {
        return new Product(
                editProductDto.getId(),
                editProductDto.getName(),
                editProductDto.getCount(),
                editProductDto.getNetPrice(),
                EMPTY_NET_VALUE,
                EMPTY_VAT_VALUE,
                EMPTY_VAT_SUM,
                EMPTY_GROSS_VALUE,
                EMPTY_INVOICE
        );
    }
}
