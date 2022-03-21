package it.marczuk.invoicemanager.domain.invoice.adapter;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceServicePort;
import it.marczuk.invoicemanager.domain.invoice.service.InvoiceService;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.ReturnInvoiceDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class InvoiceServiceAdapter implements InvoiceServicePort {

    private final InvoiceService invoiceService;

    @Override
    public List<ReturnInvoiceDto> getInvoices() {
        return invoiceService.getInvoices();
    }

    @Override
    public ReturnInvoiceDto getInvoiceById(Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @Override
    public ReturnInvoiceDto addInvoice(Invoice invoice, List<Product> productList) {
        return invoiceService.addInvoice(invoice, productList);
    }

    @Override
    public ReturnInvoiceDto editInvoice(Invoice invoice) {
        return invoiceService.editInvoice(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceService.deleteInvoice(id);
    }
}
