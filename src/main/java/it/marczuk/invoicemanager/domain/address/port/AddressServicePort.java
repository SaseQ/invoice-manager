package it.marczuk.invoicemanager.domain.address.port;

import it.marczuk.invoicemanager.domain.address.model.Address;

import java.util.List;

public interface AddressServicePort {

    List<Address> getAddresses();

    Address addAddress(Address address);
}
