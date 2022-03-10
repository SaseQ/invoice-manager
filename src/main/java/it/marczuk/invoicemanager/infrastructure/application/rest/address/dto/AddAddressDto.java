package it.marczuk.invoicemanager.infrastructure.application.rest.address.dto;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddAddressDto {

    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;
    private CountryCode country;
}
