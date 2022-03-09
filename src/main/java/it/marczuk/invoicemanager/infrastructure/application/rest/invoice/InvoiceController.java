package it.marczuk.invoicemanager.infrastructure.application.rest.invoice;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.DtoMapper.InvoiceMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.InvoiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceServicePort invoiceServicePort;

    @GetMapping("/invoices")
    public List<Invoice> getInvoices() {
        return invoiceServicePort.getInvoices();
    }

    @PostMapping("/invoice")
    public Invoice addInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceServicePort.addInvoice(InvoiceMapper.mapToInvoice(invoiceDto));
    }
}
