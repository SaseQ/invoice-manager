package it.marczuk.invoicemanager.infrastructure.persistance.database.address;

import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.port.AddressRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.persistance.database.address.entity.AddressEntity;
import it.marczuk.invoicemanager.infrastructure.persistance.database.address.entity.AddressEntityMapper;
import it.marczuk.invoicemanager.infrastructure.persistance.database.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AddressDatabaseAdapter implements AddressRepositoryPort {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> findAll() {
        return AddressEntityMapper.mapToAddress(addressRepository.findAll());
    }

    @Override
    public Optional<Address> findById(Long id) {
        return AddressEntityMapper.mapToAddress(addressRepository.findById(id));
    }

    @Override
    public Address save(Address address) {
        AddressEntity result = addressRepository.save(AddressEntityMapper.mapToAddressEntity(address));
        return AddressEntityMapper.mapToAddress(result);
    }
}
