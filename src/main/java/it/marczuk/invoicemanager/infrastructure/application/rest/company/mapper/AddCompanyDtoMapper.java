package it.marczuk.invoicemanager.infrastructure.application.rest.company.mapper;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.mapper.AddAddressDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.AddCompanyDto;

public class AddCompanyDtoMapper {

    private static final Long EMPTY_ID = null;

    private AddCompanyDtoMapper() {
    }

    public static Company mapToCompany(AddCompanyDto addCompanyDto) {
        return mapToCompanyFunction(addCompanyDto);
    }

    private static Company mapToCompanyFunction(AddCompanyDto addCompanyDto) {
        return new Company(
                EMPTY_ID,
                addCompanyDto.getCompanyName(),
                addCompanyDto.getCompanyOwnerFirstName(),
                addCompanyDto.getCompanyOwnerSecondName(),
                addCompanyDto.getTaxIdentificationNumber(),
                AddAddressDtoMapper.mapToAddress(addCompanyDto.getAddress())
        );
    }
}
