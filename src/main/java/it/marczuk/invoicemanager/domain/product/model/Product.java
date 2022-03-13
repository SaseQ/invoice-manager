package it.marczuk.invoicemanager.domain.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private Integer count;
    private BigDecimal netPrice;
    private BigDecimal netValue;
    private Integer taxValue;
    private BigDecimal taxSum;
    private BigDecimal grossValue;
}
