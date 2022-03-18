package it.marczuk.invoicemanager.domain.company.port;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.EditCompanyDto;

import java.util.List;

public interface CompanyServicePort {

    List<Company> getCompanies();

    Company getCompanyById(Long id);

    Company addCompany(Company company);

    Company editCompany(EditCompanyDto editCompanyDto);

    void deleteCompany(Long id);
}
