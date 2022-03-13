package it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.entity;

import it.marczuk.invoicemanager.domain.invoice.model.PayType;
import it.marczuk.invoicemanager.infrastructure.persistance.database.company.entity.CompanyEntity;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placeOfIssue;
    private LocalDate dateOfIssue;
    private LocalDate datePerformanceOfService;
    @ManyToOne
    private CompanyEntity seller;
    @ManyToOne
    private CompanyEntity buyer;
    @OneToMany(mappedBy = "invoice")
    private List<ProductEntity> products;
    private PayType payType;
    private LocalDate paymentDeadline;
    private BigDecimal sumToPay;
    private String sumToPayAsWords;

}
