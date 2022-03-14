package it.marczuk.invoicemanager.infrastructure.persistance.database.company;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.persistance.database.company.entity.CompanyEntity;
import it.marczuk.invoicemanager.infrastructure.persistance.database.company.entity.CompanyEntityMapper;
import it.marczuk.invoicemanager.infrastructure.persistance.database.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CompanyDatabaseAdapter implements CompanyRepositoryPort {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return CompanyEntityMapper.mapToCompany(companyRepository.findAll());
    }

    @Override
    public Optional<Company> findById(Long id) {
        return CompanyEntityMapper.mapToCompany(companyRepository.findById(id));
    }

    @Override
    public Company save(Company company) {
        CompanyEntity result = companyRepository.save(CompanyEntityMapper.mapToCompanyEntity(company));
        return CompanyEntityMapper.mapToCompany(result);
    }
}
