package it.marczuk.invoicemanager.domain.invoice.port;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;

import java.util.List;

public interface InvoiceRepositoryPort {

    List<Invoice> findAll();

    Invoice save(Invoice invoice);

}
