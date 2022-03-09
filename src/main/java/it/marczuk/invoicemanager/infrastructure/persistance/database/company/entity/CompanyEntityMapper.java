package it.marczuk.invoicemanager.infrastructure.persistance.database.company.entity;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.infrastructure.persistance.database.address.entity.AddressEntityMapper;

public class CompanyEntityMapper {

    private CompanyEntityMapper() {
    }

    public static Company mapToCompany(CompanyEntity companyEntity) {
        return mapToCompanyFunction(companyEntity);
    }

    public static CompanyEntity mapToCompanyEntity(Company company) {
        return mapToCompanyEntityFunction(company);
    }

    private static Company mapToCompanyFunction(CompanyEntity companyEntity) {
        return new Company(
                companyEntity.getId(),
                companyEntity.getCompanyName(),
                companyEntity.getCompanyOwnerFirstName(),
                companyEntity.getCompanyOwnerSecondName(),
                companyEntity.getTaxIdentificationNumber(),
                AddressEntityMapper.mapToAddress(companyEntity.getAddress())
        );
    }

    private static CompanyEntity mapToCompanyEntityFunction(Company company) {
        return new CompanyEntity(
                company.getId(),
                company.getCompanyName(),
                company.getCompanyOwnerFirstName(),
                company.getCompanyOwnerSecondName(),
                company.getTaxIdentificationNumber(),
                AddressEntityMapper.mapToAddressEntity(company.getAddress())
        );
    }
}
