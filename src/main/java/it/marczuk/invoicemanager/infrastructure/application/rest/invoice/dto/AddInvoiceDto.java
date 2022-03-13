package it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto;

import io.swagger.annotations.ApiModelProperty;
import it.marczuk.invoicemanager.domain.invoice.model.PayType;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.AddCompanyDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.AddProductDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class AddInvoiceDto {

    private String placeOfIssue;
    @ApiModelProperty(example = "2003-03-03")
    private LocalDate dateOfIssue;
    @ApiModelProperty(example = "2003-03-03")
    private LocalDate datePerformanceOfService;
    private AddCompanyDto seller;
    private AddCompanyDto buyer;
    private List<AddProductDto> products;
    private PayType payType;
    @ApiModelProperty(example = "2003-03-03")
    private LocalDate paymentDeadline;
    private String payAccountNumber;

}
