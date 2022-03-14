package it.marczuk.invoicemanager.infrastructure.application.rest.invoice;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.AddInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper.InvoiceMapper;
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

    @GetMapping("/get/{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {
        return invoiceServicePort.getInvoiceById(id);
    }

    @PostMapping("/add")
    public Invoice addInvoice(@RequestBody AddInvoiceDto invoiceDto) {
        return invoiceServicePort.addInvoice(InvoiceMapper.mapToInvoice(invoiceDto));
    }
}
