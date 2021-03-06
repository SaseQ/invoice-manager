package it.marczuk.invoicemanager.domain.company.adapter;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyServicePort;
import it.marczuk.invoicemanager.domain.company.service.CompanyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CompanyServiceAdapter implements CompanyServicePort {

    private final CompanyService companyService;

    @Override
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyService.getCompanyById(id);
    }

    @Override
    public Company addCompany(Company company) {
        return companyService.addCompany(company);
    }

    @Override
    public Company editCompany(Company company) {
        return companyService.editCompany(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyService.deleteCompany(id);
    }
}
