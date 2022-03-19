package it.marczuk.invoicemanager.domain.company.service;

import it.marczuk.invoicemanager.domain.address.service.TestAddressData;
import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.EditCompanyDto;

public class TestCompanyData {

    private TestCompanyData() {
    }

    public static Company getTestCompany(Long id) {
        return createTestCompany(id);
    }

    public static EditCompanyDto mapToEditCompanyDto(Company company) {
        return EditCompanyDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyOwnerFirstName(company.getCompanyOwnerFirstName())
                .companyOwnerSecondName(company.getCompanyOwnerSecondName())
                .taxIdentificationNumber(company.getTaxIdentificationNumber())
                .addressId(company.getAddress().getId())
                .build();
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
