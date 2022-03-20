package it.marczuk.invoicemanager.domain.product.service;

import it.marczuk.invoicemanager.infrastructure.webclient.tax.dto.TaxDto;

public class TestTaxDtoData {

    private TestTaxDtoData() {
    }

    public static TaxDto getTaxDtoData() {
        return createTaxDtoData(23, "PL", "POLAND", "PLN");
    }

    public static TaxDto getEditTaxDtoData() {
        return createTaxDtoData(19, "DE", "GERMANY", "EUR");
    }

    private static TaxDto createTaxDtoData(Integer standardRate,
                                           String countryCode,
                                           String countryName,
                                           String currency) {
        return TaxDto.builder()
                .standardRate(standardRate)
                .countryCode(countryCode)
                .countryName(countryName)
                .currency(currency)
                .build();
    }
}
