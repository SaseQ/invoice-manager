package it.marczuk.invoicemanager.infrastructure.application.rest.product.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EditProductDto {

    private Long id;
    private String name;
    private Integer count;
    private BigDecimal netPrice;
}