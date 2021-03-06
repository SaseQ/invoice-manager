package it.marczuk.invoicemanager.domain.invoice.model;

import it.marczuk.invoicemanager.domain.company.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Invoice {

    private Long id;
    private String placeOfIssue;
    private LocalDate dateOfIssue;
    private LocalDate datePerformanceOfService;
    private Company seller;
    private Company buyer;
    private PayType payType;
    private LocalDate paymentDeadline;
    private BigDecimal sumToPay;
    private String sumToPayAsWords;
}
