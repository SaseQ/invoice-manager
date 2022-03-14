package it.marczuk.invoicemanager.domain.invoice.port;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;

import java.util.List;

public interface InvoiceServicePort {

    List<Invoice> getInvoices();

    Invoice getInvoiceById(Long id);

    Invoice addInvoice(Invoice invoice);

}
