package it.marczuk.invoicemanager.infrastructure.application.rest.company.mapper;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.mapper.EditAddressDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.EditCompanyDto;

public class EditCompanyDtoMapper {

    private EditCompanyDtoMapper() {
    }

    public static Company mapToCompany(EditCompanyDto editCompanyDto) {
        return mapToCompanyFunction(editCompanyDto);
    }

    private static Company mapToCompanyFunction(EditCompanyDto editCompanyDto) {
        return new Company(
                editCompanyDto.getId(),
                editCompanyDto.getCompanyName(),
                editCompanyDto.getCompanyOwnerFirstName(),
                editCompanyDto.getCompanyOwnerSecondName(),
                editCompanyDto.getTaxIdentificationNumber(),
                EditAddressDtoMapper.mapToAddress(editCompanyDto.getAddress())
        );
    }
}
