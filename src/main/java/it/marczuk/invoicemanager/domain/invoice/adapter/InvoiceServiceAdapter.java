package it.marczuk.invoicemanager.domain.invoice.adapter;

import it.marczuk.invoicemanager.domain.invoice.port.InvoiceServicePort;
import it.marczuk.invoicemanager.domain.invoice.service.InvoiceService;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.AddInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.EditInvoiceDto;
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
    public ReturnInvoiceDto addInvoice(AddInvoiceDto addInvoiceDto) {
        return invoiceService.addInvoice(addInvoiceDto);
    }

    @Override
    public ReturnInvoiceDto editInvoice(EditInvoiceDto editInvoiceDto) {
        return invoiceService.editInvoice(editInvoiceDto);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceService.deleteInvoice(id);
    }
}
