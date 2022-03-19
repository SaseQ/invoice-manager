package it.marczuk.invoicemanager.domain.company.service;

import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.port.AddressServicePort;
import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.EditCompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepositoryPort companyRepositoryPort;
    private final AddressServicePort addressServicePort;

    private static final String COMPANY_ERROR_MESSAGE = "Could not find company by id: ";

    public List<Company> getCompanies() {
        return companyRepositoryPort.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepositoryPort.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(COMPANY_ERROR_MESSAGE + id));
    }

    public Company addCompany(Company company) {
        Address address = addressServicePort.addAddress(company.getAddress());
        company.setAddress(address);

        return companyRepositoryPort.save(company);
    }

    public Company editCompany(EditCompanyDto editCompanyDto) {
        Company companyEdited = companyRepositoryPort.findById(editCompanyDto.getId())
                .orElseThrow(() -> new ElementNotFoundException(COMPANY_ERROR_MESSAGE + editCompanyDto.getId()));
        Address addressEdited = addressServicePort.getAddressById(editCompanyDto.getAddressId());

        companyEdited.setCompanyName(editCompanyDto.getCompanyName());
        companyEdited.setCompanyOwnerFirstName(editCompanyDto.getCompanyOwnerFirstName());
        companyEdited.setCompanyOwnerSecondName(editCompanyDto.getCompanyOwnerSecondName());
        companyEdited.setTaxIdentificationNumber(editCompanyDto.getTaxIdentificationNumber());
        companyEdited.setAddress(addressEdited);

        return companyRepositoryPort.save(companyEdited);
    }

    public void deleteCompany(Long id) {
        Company company = companyRepositoryPort.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(COMPANY_ERROR_MESSAGE + id));
        Address address = addressServicePort.getAddressById(company.getAddress().getId());

        companyRepositoryPort.delete(company);
        addressServicePort.deleteAddress(address.getId());
    }
}
