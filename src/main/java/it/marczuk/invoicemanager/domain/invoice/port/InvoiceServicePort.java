package it.marczuk.invoicemanager.domain.invoice.port;

import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.AddInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.EditInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.ReturnInvoiceDto;

import java.util.List;

public interface InvoiceServicePort {

    List<ReturnInvoiceDto> getInvoices();

    ReturnInvoiceDto getInvoiceById(Long id);

    ReturnInvoiceDto addInvoice(AddInvoiceDto addInvoiceDto);

    ReturnInvoiceDto editInvoice(EditInvoiceDto editInvoiceDto);

    void deleteInvoice(Long id);
}
