package it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.invoice.model.PayType;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.ReturnProductDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ReturnInvoiceDto {

    private Long id;
    private String placeOfIssue;
    private LocalDate dateOfIssue;
    private LocalDate datePerformanceOfService;
    private Company seller;
    private Company buyer;
    private List<ReturnProductDto> products;
    private PayType payType;
    private LocalDate paymentDeadline;
    private BigDecimal sumToPay;
    private String sumToPayAsWords;
}
