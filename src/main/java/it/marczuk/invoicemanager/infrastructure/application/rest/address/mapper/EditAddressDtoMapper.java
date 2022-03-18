package it.marczuk.invoicemanager.infrastructure.application.rest.address.mapper;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.dto.EditAddressDto;

public class EditAddressDtoMapper {

    private EditAddressDtoMapper() {
    }

    public static Address mapToAddress(EditAddressDto editAddressDto) {
        return mapToAddressFunction(editAddressDto);
    }

    private static Address mapToAddressFunction(EditAddressDto editAddressDto) {
        return new Address(
                editAddressDto.getId(),
                editAddressDto.getStreetName(),
                editAddressDto.getHouseNumber(),
                editAddressDto.getZipCode(),
                editAddressDto.getCity(),
                CountryCode.getByAlpha2Code(editAddressDto.getCountry())
        );
    }
}
