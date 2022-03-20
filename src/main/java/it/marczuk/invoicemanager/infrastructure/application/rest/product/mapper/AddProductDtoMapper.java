package it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.AddProductDto;

import java.math.BigDecimal;

public class AddProductDtoMapper {

    private static final Long EMPTY_ID = null;
    private static final BigDecimal EMPTY_NET_VALUE = null;
    private static final Integer EMPTY_VAT_VALUE = null;
    private static final BigDecimal EMPTY_VAT_SUM = null;
    private static final BigDecimal EMPTY_GROSS_VALUE = null;
    private static final Invoice EMPTY_INVOICE = null;

    private AddProductDtoMapper() {
    }

    public static Product mapToProduct(AddProductDto addProductDto) {
        return mapToProductFunction(addProductDto);
    }

    private static Product mapToProductFunction(AddProductDto addProductDto) {
        return new Product(
                EMPTY_ID,
                addProductDto.getName(),
                CountryCode.getByAlpha2Code(addProductDto.getCountry()),
                addProductDto.getCount(),
                addProductDto.getNetPrice(),
                EMPTY_NET_VALUE,
                EMPTY_VAT_VALUE,
                EMPTY_VAT_SUM,
                EMPTY_GROSS_VALUE,
                EMPTY_INVOICE
        );
    }
}
