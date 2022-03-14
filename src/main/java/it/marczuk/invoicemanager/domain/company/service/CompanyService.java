package it.marczuk.invoicemanager.domain.company.service;

import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.port.AddressRepositoryPort;
import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepositoryPort companyRepositoryPort;
    private final AddressRepositoryPort addressRepositoryPort;

    public List<Company> getCompanies() {
        return companyRepositoryPort.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepositoryPort.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Could not find company by id: " + id));
    }

    public Company addCompany(Company company) {
        Address address = addressRepositoryPort.save(company.getAddress());
        company.setAddress(address);

        return companyRepositoryPort.save(company);
    }
}
