package it.marczuk.invoicemanager.domain.invoice.port;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.ReturnInvoiceDto;

import java.util.List;

public interface InvoiceServicePort {

    List<ReturnInvoiceDto> getInvoices();

    ReturnInvoiceDto getInvoiceById(Long id);

    ReturnInvoiceDto addInvoice(Invoice invoice, List<Product> productList);

    ReturnInvoiceDto editInvoice(Invoice invoice);

    void deleteInvoice(Long id);
}
