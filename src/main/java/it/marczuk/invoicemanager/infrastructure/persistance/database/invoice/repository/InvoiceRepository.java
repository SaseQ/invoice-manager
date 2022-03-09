package it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.repository;

import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
}
