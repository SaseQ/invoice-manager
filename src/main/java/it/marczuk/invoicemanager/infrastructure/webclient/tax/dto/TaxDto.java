package it.marczuk.invoicemanager.infrastructure.webclient.tax.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaxDto {

    private Integer standardRate;
    private String countryCode;
    private String countryName;
    private String currency;

}
