package it.marczuk.invoicemanager.domain.company.port;

import it.marczuk.invoicemanager.domain.company.model.Company;

import java.util.List;

public interface CompanyServicePort {

    List<Company> getCompanies();

    Company getCompanyById(Long id);

    Company addCompany(Company company);

}
