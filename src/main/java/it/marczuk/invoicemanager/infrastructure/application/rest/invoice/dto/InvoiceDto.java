package it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.invoice.model.PayType;
import it.marczuk.invoicemanager.domain.product.model.Product;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class InvoiceDto {

    private String placeOfIssue;
    private LocalDate dateOfIssue;
    private LocalDate datePerformanceOfService;
    private Company seller;
    private Company buyer;
    private Product product;
    private PayType payType;
    private LocalDate paymentDeadline;
    private String payAccountNumber;
    private BigDecimal sumToPay;

}
