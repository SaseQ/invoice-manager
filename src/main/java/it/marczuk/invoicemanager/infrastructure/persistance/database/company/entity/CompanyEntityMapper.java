package it.marczuk.invoicemanager.infrastructure.persistance.database.company.entity;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.infrastructure.persistance.database.address.entity.AddressEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompanyEntityMapper {

    private CompanyEntityMapper() {
    }

    public static List<Company> mapToCompany(List<CompanyEntity> allCompanyEntities) {
        return allCompanyEntities
                .stream()
                .map(CompanyEntityMapper::mapToCompanyFunction)
                .collect(Collectors.toList());
    }

    public static List<CompanyEntity> mapToCompanyEntity(List<Company> allCompanies) {
        return allCompanies
                .stream()
                .map(CompanyEntityMapper::mapToCompanyEntityFunction)
                .collect(Collectors.toList());
    }

    public static Company mapToCompany(CompanyEntity companyEntity) {
        return mapToCompanyFunction(companyEntity);
    }

    public static CompanyEntity mapToCompanyEntity(Company company) {
        return mapToCompanyEntityFunction(company);
    }

    public static Optional<Company> mapToCompany(Optional<CompanyEntity> companyEntityOptional) {
        return companyEntityOptional
                .map(CompanyEntityMapper::mapToCompanyFunction);
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
