package it.marczuk.invoicemanager.domain.address.port;

import it.marczuk.invoicemanager.domain.address.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepositoryPort {

    List<Address> findAll();

    Optional<Address> findById(Long id);

    Address save(Address address);

    void delete(Address address);
}
