package it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto;

import io.swagger.annotations.ApiModelProperty;
import it.marczuk.invoicemanager.domain.invoice.model.PayType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EditInvoiceDto {

    private Long id;
    private String placeOfIssue;
    @ApiModelProperty(example = "2003-03-03")
    private LocalDate dateOfIssue;
    @ApiModelProperty(example = "2003-03-03")
    private LocalDate datePerformanceOfService;
    private Long sellerId;
    private Long buyerId;
    private PayType payType;
    @ApiModelProperty(example = "2003-03-03")
    private LocalDate paymentDeadline;
    private String payAccountNumber;
}
