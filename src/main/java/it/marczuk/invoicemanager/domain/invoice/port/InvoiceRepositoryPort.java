package it.marczuk.invoicemanager.domain.invoice.port;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepositoryPort {

    List<Invoice> findAll();

    Optional<Invoice> findById(Long id);

    Invoice save(Invoice invoice);

}
