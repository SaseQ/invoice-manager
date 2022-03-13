package it.marczuk.invoicemanager.infrastructure.application.rest.company.dto;

import it.marczuk.invoicemanager.infrastructure.application.rest.address.dto.AddAddressDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddCompanyDto {

    private String companyName;
    private String companyOwnerFirstName;
    private String companyOwnerSecondName;
    private String taxIdentificationNumber;
    private AddAddressDto address;
}
