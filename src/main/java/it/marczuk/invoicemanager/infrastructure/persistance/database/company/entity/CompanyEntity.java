package it.marczuk.invoicemanager.infrastructure.persistance.database.company.entity;

import it.marczuk.invoicemanager.infrastructure.persistance.database.address.entity.AddressEntity;
import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.entity.InvoiceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String companyOwnerFirstName;
    private String companyOwnerSecondName;
    private String taxIdentificationNumber;
    @OneToOne
    private AddressEntity address;

    @OneToMany(mappedBy = "seller")
    private List<InvoiceEntity> sellers;
    @OneToMany(mappedBy = "buyer")
    private List<InvoiceEntity> buyers;

    public CompanyEntity(Long id, String companyName, String companyOwnerFirstName, String companyOwnerSecondName, String taxIdentificationNumber, AddressEntity address) {
        this.id = id;
        this.companyName = companyName;
        this.companyOwnerFirstName = companyOwnerFirstName;
        this.companyOwnerSecondName = companyOwnerSecondName;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.address = address;
    }
}
