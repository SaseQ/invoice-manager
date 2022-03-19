package it.marczuk.invoicemanager.domain.address.service;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.address.model.Address;

import java.util.List;

public class TestAddressData {

    private TestAddressData() {
    }

    public static Address getTestAddress(Long id) {
        return createTestAddress(id);
    }

    private static Address createTestAddress(Long id) {
        return new Address(
                id,
                "streetName",
                "11",
                "09-032",
                "city",
                CountryCode.PL
        );
    }
}
