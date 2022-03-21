package it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto;

import io.swagger.annotations.ApiModelProperty;
import it.marczuk.invoicemanager.domain.invoice.model.PayType;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.EditCompanyDto;
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
    private EditCompanyDto seller;
    private EditCompanyDto buyer;
    private PayType payType;
    @ApiModelProperty(example = "2003-03-03")
    private LocalDate paymentDeadline;
}
