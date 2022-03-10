package it.marczuk.invoicemanager.infrastructure.application.rest.product.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AddProductDto {

    private String name;
    private Integer count;
    private BigDecimal netPrice;
    private Double vatValue;
    private BigDecimal vatSum;
    private BigDecimal grossPrice;
}
