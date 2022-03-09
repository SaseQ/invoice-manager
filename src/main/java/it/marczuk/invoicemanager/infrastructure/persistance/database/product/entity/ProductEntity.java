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
    private Double vatValue;
    private BigDecimal vatSum;
    private BigDecimal grossPrice;

    @OneToOne(mappedBy = "product")
    private InvoiceEntity invoice;

    public ProductEntity(Long id, String name, Integer count, BigDecimal netPrice, Double vatValue, BigDecimal vatSum, BigDecimal grossPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.netPrice = netPrice;
        this.vatValue = vatValue;
        this.vatSum = vatSum;
        this.grossPrice = grossPrice;
    }
}
