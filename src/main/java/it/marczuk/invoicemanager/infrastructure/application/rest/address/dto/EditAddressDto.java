package it.marczuk.invoicemanager.infrastructure.application.rest.address.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditAddressDto {

    private Long id;
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;
    @ApiModelProperty(example = "PL")
    private String country;
}
