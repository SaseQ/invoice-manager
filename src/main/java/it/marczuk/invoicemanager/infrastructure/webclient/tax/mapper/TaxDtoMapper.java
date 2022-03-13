package it.marczuk.invoicemanager.infrastructure.webclient.tax.mapper;

import it.marczuk.invoicemanager.infrastructure.webclient.tax.dto.TaxDto;
import it.marczuk.invoicemanager.infrastructure.webclient.tax.dto.VatStackDto;
import it.marczuk.invoicemanager.infrastructure.webclient.tax.dto.VatStackRatesDto;

public class TaxDtoMapper {

    private TaxDtoMapper() {
    }

    public static TaxDto mapToTaxDto(VatStackDto vatStackDto) {
        return mapToTaxDtoFunction(vatStackDto);
    }

    private static TaxDto mapToTaxDtoFunction(VatStackDto vatStackDto) {
        VatStackRatesDto vatStackRatesDto = getVatStackRatesDto(vatStackDto);
        return TaxDto.builder()
                .standardRate(vatStackRatesDto.getStandardRate())
                .countryCode(vatStackRatesDto.getCountryCode())
                .countryName(vatStackRatesDto.getCountryName())
                .currency(vatStackRatesDto.getCurrency())
                .build();
    }

    private static VatStackRatesDto getVatStackRatesDto(VatStackDto vatStackDto) {
        int ratesSize = vatStackDto.getRates().size();
        if(ratesSize == 1) {
            return vatStackDto.getRates().get(0);
        }
        if(ratesSize > 1) {
            throw new RuntimeException("Rates contains many positions!");
        }
        throw new RuntimeException("Rates not exist!");
    }
}
