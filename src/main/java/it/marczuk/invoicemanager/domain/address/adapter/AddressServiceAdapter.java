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
    public Address getAddressById(Long id) {
        return addressService.getAddressById(id);
    }

    @Override
    public Address addAddress(Address address) {
        return addressService.addAddress(address);
    }

    @Override
    public Address editAddress(Address address) {
        return addressService.editAddress(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressService.deleteAddress(id);
    }
}
