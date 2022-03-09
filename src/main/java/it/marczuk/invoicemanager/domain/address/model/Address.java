package it.marczuk.invoicemanager.domain.address.model;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private Long id;
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;
    private CountryCode country;
}
