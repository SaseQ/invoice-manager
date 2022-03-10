package it.marczuk.invoicemanager.domain.address.adapter;

import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.port.AddressServicePort;
import it.marczuk.invoicemanager.domain.address.service.AddressService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AddressServiceAdapter implements AddressServicePort {

    private final AddressService addressService;

    @Override
    public List<Address> getAddresses() {
        return addressService.getAddresses();
    }

    @Override
    public Address addAddress(Address address) {
        return addressService.addAddress(address);
    }
}
