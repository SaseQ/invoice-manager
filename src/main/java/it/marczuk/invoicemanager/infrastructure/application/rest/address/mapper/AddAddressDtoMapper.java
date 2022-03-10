package it.marczuk.invoicemanager.infrastructure.application.rest.address.mapper;

import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.dto.AddAddressDto;

public class AddAddressDtoMapper {

    private static final Long EMPTY_ID = null;

    private AddAddressDtoMapper() {
    }

    public static Address mapToAddress(AddAddressDto addAddressDto) {
        return mapToAddressFunction(addAddressDto);
    }

    private static Address mapToAddressFunction(AddAddressDto addAddressDto) {
        return new Address(
                EMPTY_ID,
                addAddressDto.getStreetName(),
                addAddressDto.getHouseNumber(),
                addAddressDto.getZipCode(),
                addAddressDto.getCity(),
                addAddressDto.getCountry()
        );
    }
}
