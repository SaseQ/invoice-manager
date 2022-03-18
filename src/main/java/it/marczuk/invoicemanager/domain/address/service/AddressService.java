package it.marczuk.invoicemanager.domain.address.service;

import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.port.AddressRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class AddressService {

    private final AddressRepositoryPort addressRepositoryPort;
    private static final String ADDRESS_ERROR_MESSAGE = "Could not find address by id: ";

    public List<Address> getAddresses() {
        return addressRepositoryPort.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepositoryPort.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(ADDRESS_ERROR_MESSAGE + id));
    }

    public Address addAddress(Address address) {
        return addressRepositoryPort.save(address);
    }

    @Transactional
    public Address editAddress(Address address) {
        Address addressEdited = addressRepositoryPort.findById(address.getId())
                .orElseThrow(() -> new ElementNotFoundException(ADDRESS_ERROR_MESSAGE + address.getId()));

        addressEdited.setStreetName(address.getStreetName());
        addressEdited.setHouseNumber(address.getHouseNumber());
        addressEdited.setZipCode(address.getZipCode());
        addressEdited.setCity(address.getCity());
        addressEdited.setCountry(address.getCountry());

        return addressRepositoryPort.save(addressEdited);
    }

    public void deleteAddress(Long id) {
        Address address = addressRepositoryPort.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(ADDRESS_ERROR_MESSAGE + id));

        addressRepositoryPort.delete(address);
    }
}
