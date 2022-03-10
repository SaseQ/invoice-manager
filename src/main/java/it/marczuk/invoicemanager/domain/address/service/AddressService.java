package it.marczuk.invoicemanager.domain.address.service;

import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.port.AddressRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AddressService {

    private final AddressRepositoryPort addressRepositoryPort;

    public List<Address> getAddresses() {
        return addressRepositoryPort.findAll();
    }

    public Address addAddress(Address address) {
        return addressRepositoryPort.save(address);
    }
}
