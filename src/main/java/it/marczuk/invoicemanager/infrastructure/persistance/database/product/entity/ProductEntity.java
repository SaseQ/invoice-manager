package it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity;

import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.entity.InvoiceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer count;
    private BigDecimal netPrice;
    private BigDecimal netValue;
    private Integer taxValue;
    private BigDecimal taxSum;
    private BigDecimal grossValue;

    @ManyToOne
    private InvoiceEntity invoice;

    public ProductEntity(Long id, String name, Integer count, BigDecimal netPrice, BigDecimal netValue, Integer taxValue, BigDecimal taxSum, BigDecimal grossValue) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.netPrice = netPrice;
        this.netValue = netValue;
        this.taxValue = taxValue;
        this.taxSum = taxSum;
        this.grossValue = grossValue;
    }
}
