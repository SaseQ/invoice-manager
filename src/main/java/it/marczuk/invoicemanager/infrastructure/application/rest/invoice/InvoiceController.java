package it.marczuk.invoicemanager.infrastructure.application.rest.invoice;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceServicePort;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.AddInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.EditInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.ReturnInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper.EditInvoiceDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper.InvoiceMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper.AddProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice_manager/invoice")
public class InvoiceController {

    private final InvoiceServicePort invoiceServicePort;

    @GetMapping("/get_all")
    public List<ReturnInvoiceDto> getInvoices() {
        return invoiceServicePort.getInvoices();
    }

    @GetMapping("/get/{id}")
    public ReturnInvoiceDto getInvoiceById(@PathVariable Long id) {
        return invoiceServicePort.getInvoiceById(id);
    }

    @PostMapping("/add")
    public ReturnInvoiceDto addInvoice(@RequestBody AddInvoiceDto addInvoiceDto) {
        Invoice invoice = InvoiceMapper.mapToInvoice(addInvoiceDto);
        List<Product> products = AddProductDtoMapper.mapToProduct(addInvoiceDto.getProducts());
        return invoiceServicePort.addInvoice(invoice, products);
    }

    @PutMapping("/update")
    public ReturnInvoiceDto editInvoice(@RequestBody EditInvoiceDto editInvoiceDto) {
        return invoiceServicePort.editInvoice(EditInvoiceDtoMapper.mapToInvoice(editInvoiceDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceServicePort.deleteInvoice(id);
    }
}
