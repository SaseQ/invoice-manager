package it.marczuk.invoicemanager.domain.product.model;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private CountryCode country;
    private Integer count;
    private BigDecimal netPrice;
    private BigDecimal netValue;
    private Integer taxValue;
    private BigDecimal taxSum;
    private BigDecimal grossValue;
    private Invoice invoice;
}
