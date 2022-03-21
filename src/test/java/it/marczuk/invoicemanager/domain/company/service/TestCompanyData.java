package it.marczuk.invoicemanager.domain.company.service;

import it.marczuk.invoicemanager.domain.address.service.TestAddressData;
import it.marczuk.invoicemanager.domain.company.model.Company;

public class TestCompanyData {

    private TestCompanyData() {
    }

    public static Company getTestCompany(Long id) {
        return createTestCompany(id);
    }

    private static Company createTestCompany(Long id) {
        return new Company(
                id,
                "companyName",
                "companyOwnerFirstName",
                "companyOwnerSecondName",
                "1234567890",
                TestAddressData.getTestAddress(id)
        );
    }
}
