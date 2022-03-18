package it.marczuk.invoicemanager.domain.company.port;

import it.marczuk.invoicemanager.domain.company.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepositoryPort {

    List<Company> findAll();

    Optional<Company> findById(Long id);

    Company save(Company company);

    void delete(Company company);
}
