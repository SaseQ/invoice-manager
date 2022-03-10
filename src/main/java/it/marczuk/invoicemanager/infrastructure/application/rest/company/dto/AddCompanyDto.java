package it.marczuk.invoicemanager.infrastructure.application.rest.company.dto;

import it.marczuk.invoicemanager.domain.address.model.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddCompanyDto {

    private String companyName;
    private String companyOwnerFirstName;
    private String companyOwnerSecondName;
    private String taxIdentificationNumber;
    private Address address;
}
