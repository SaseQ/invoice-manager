package it.marczuk.invoicemanager.domain.invoice.adapter;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceServicePort;
import it.marczuk.invoicemanager.domain.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class InvoiceServiceAdapter implements InvoiceServicePort {

    private final InvoiceService invoiceService;

    @Override
    public List<Invoice> getInvoices() {
        return invoiceService.getInvoices();
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoiceService.addInvoice(invoice);
    }
}
