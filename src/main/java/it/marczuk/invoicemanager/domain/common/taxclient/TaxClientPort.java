package it.marczuk.invoicemanager.domain.common.taxclient;

import it.marczuk.invoicemanager.infrastructure.webclient.tax.dto.TaxDto;

public interface TaxClientPort {

    TaxDto getTax(String countryCode);

}
