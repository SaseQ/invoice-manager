package it.marczuk.invoicemanager.domain.company.service;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepositoryPort companyRepositoryPort;

    public List<Company> getCompanies() {
        return companyRepositoryPort.findAll();
    }

    public Company addCompany(Company company) {
        return companyRepositoryPort.save(company);
    }
}
