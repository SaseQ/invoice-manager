package it.marczuk.invoicemanager.infrastructure.application.rest.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AddProductDto {

    private String name;
    @ApiModelProperty(example = "PL")
    private String country;
    private Integer count;
    private BigDecimal netPrice;
}
