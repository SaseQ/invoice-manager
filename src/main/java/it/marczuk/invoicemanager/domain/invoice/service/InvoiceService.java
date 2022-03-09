package it.marczuk.invoicemanager.domain.invoice.service;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepositoryPort invoiceRepositoryPort;
    private final EmailNotificationPort emailNotificationPort;

    public List<Invoice> getInvoices() {
        return invoiceRepositoryPort.findAll();
    }

    public Invoice addInvoice(Invoice invoice) {
        Invoice result = invoiceRepositoryPort.save(invoice);
        emailNotificationPort.send(List.of("saseq.pl@gmail.com"), "Invoice id=" + result.getId() + " was added.");
        return result;
    }
}
