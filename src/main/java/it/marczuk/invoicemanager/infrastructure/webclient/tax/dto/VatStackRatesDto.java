package it.marczuk.invoicemanager.infrastructure.webclient.tax.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VatStackRatesDto {

    @JsonProperty("standard_rate")
    private Integer standardRate;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("currency")
    private String currency;
}
