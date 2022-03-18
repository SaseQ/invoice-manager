package it.marczuk.invoicemanager.infrastructure.application.rest.company.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditCompanyDto {

    private Long id;
    private String companyName;
    private String companyOwnerFirstName;
    private String companyOwnerSecondName;
    private String taxIdentificationNumber;
    private Long addressId;
}
