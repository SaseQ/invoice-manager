package it.marczuk.invoicemanager.infrastructure.persistance.database.address.repository;

import it.marczuk.invoicemanager.infrastructure.persistance.database.address.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
