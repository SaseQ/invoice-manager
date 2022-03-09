package it.marczuk.invoicemanager.infrastructure.persistance.database.address.entity;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.address.model.Address;

public class AddressEntityMapper {

    private AddressEntityMapper() {
    }

    public static Address mapToAddress(AddressEntity addressEntity) {
        return mapToAddressFunction(addressEntity);
    }

    public static AddressEntity mapToAddressEntity(Address address) {
        return mapToAddressEntityFunction(address);
    }

    private static Address mapToAddressFunction(AddressEntity addressEntity) {
        return new Address(
                addressEntity.getId(),
                addressEntity.getStreetName(),
                addressEntity.getHouseNumber(),
                addressEntity.getZipCode(),
                addressEntity.getCity(),
                CountryCode.getByAlpha4Code(addressEntity.getCountry())
        );
    }

    private static AddressEntity mapToAddressEntityFunction(Address address) {
        return new AddressEntity(
                address.getId(),
                address.getStreetName(),
                address.getHouseNumber(),
                address.getZipCode(),
                address.getCity(),
                address.getCountry().getName()
        );
    }
}
