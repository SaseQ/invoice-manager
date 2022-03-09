package it.marczuk.invoicemanager.domain.company.model;

import it.marczuk.invoicemanager.domain.address.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Company {

    private Long id;
    private String companyName;
    private String companyOwnerFirstName;
    private String companyOwnerSecondName;
    private String taxIdentificationNumber; //NIP
    private Address address;
}
