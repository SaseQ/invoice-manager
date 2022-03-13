package it.marczuk.invoicemanager.infrastructure.webclient.tax.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class VatStackDto {

    @JsonProperty("rates")
    private List<VatStackRatesDto> rates;
}
