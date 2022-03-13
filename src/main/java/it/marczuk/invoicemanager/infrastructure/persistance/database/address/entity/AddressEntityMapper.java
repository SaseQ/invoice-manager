package it.marczuk.invoicemanager.infrastructure.persistance.database.address.entity;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.address.model.Address;

import java.util.List;
import java.util.stream.Collectors;

public class AddressEntityMapper {

    private AddressEntityMapper() {
    }

    public static List<Address> mapToAddress(List<AddressEntity> allAddressEntities) {
        return allAddressEntities
                .stream()
                .map(AddressEntityMapper::mapToAddressFunction)
                .collect(Collectors.toList());
    }

    public static List<AddressEntity> mapToAddressEntity(List<Address> allAddresses) {
        return allAddresses
                .stream()
                .map(AddressEntityMapper::mapToAddressEntityFunction)
                .collect(Collectors.toList());
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
                CountryCode.getByAlpha2Code(addressEntity.getCountry())
        );
    }

    private static AddressEntity mapToAddressEntityFunction(Address address) {
        return new AddressEntity(
                address.getId(),
                address.getStreetName(),
                address.getHouseNumber(),
                address.getZipCode(),
                address.getCity(),
                address.getCountry().getAlpha2()
        );
    }
}
