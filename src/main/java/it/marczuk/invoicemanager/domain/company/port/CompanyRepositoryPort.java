package it.marczuk.invoicemanager.domain.company.port;

import it.marczuk.invoicemanager.domain.company.model.Company;

import java.util.List;

public interface CompanyRepositoryPort {

    List<Company> findAll();

    Company save(Company company);
}
