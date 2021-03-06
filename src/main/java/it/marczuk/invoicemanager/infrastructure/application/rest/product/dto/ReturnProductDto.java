package it.marczuk.invoicemanager.infrastructure.application.rest.product.dto;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ReturnProductDto {

    private Long id;
    private String name;
    private CountryCode country;
    private Integer count;
    private BigDecimal netPrice;
    private BigDecimal netValue;
    private Integer taxValue;
    private BigDecimal taxSum;
    private BigDecimal grossValue;
}
