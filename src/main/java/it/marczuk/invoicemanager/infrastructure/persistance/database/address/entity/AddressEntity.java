package it.marczuk.invoicemanager.infrastructure.persistance.database.address.entity;

import it.marczuk.invoicemanager.infrastructure.persistance.database.company.entity.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;

    @OneToOne(mappedBy = "address")
    private CompanyEntity companyEntity;

    public AddressEntity(Long id, String streetName, String houseNumber, String zipCode, String city, String country) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }
}
