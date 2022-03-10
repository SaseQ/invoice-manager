package it.marczuk.invoicemanager.infrastructure.application.rest.invoice;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper.InvoiceMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.InvoiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice_manager/invoice")
public class InvoiceController {

    private final InvoiceServicePort invoiceServicePort;

    @GetMapping("/get_all")
    public List<Invoice> getInvoices() {
        return invoiceServicePort.getInvoices();
    }

    @PostMapping("/add")
    public Invoice addInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceServicePort.addInvoice(InvoiceMapper.mapToInvoice(invoiceDto));
    }
}
