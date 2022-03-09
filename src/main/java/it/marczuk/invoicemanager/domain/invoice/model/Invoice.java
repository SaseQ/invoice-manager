package it.marczuk.invoicemanager.domain.invoice.model;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Invoice {

    private Long id;
    private String placeOfIssue;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfIssue;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate datePerformanceOfService;
    private Company seller;
    private Company buyer;
    private Product product;
    private PayType payType;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate paymentDeadline;
    private BigDecimal sumToPay;

}
