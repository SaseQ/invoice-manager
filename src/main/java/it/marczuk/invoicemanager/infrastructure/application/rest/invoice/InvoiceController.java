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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice_manager/invoice")
public class InvoiceController {

    private final InvoiceServicePort invoiceServicePort;

    @GetMapping("/get_all")
    public ResponseEntity<List<ReturnInvoiceDto>> getInvoices() {
        return ResponseEntity.ok(invoiceServicePort.getInvoices());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ReturnInvoiceDto> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceServicePort.getInvoiceById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ReturnInvoiceDto> addInvoice(@RequestBody AddInvoiceDto addInvoiceDto) {
        Invoice invoice = InvoiceMapper.mapToInvoice(addInvoiceDto);
        List<Product> products = AddProductDtoMapper.mapToProduct(addInvoiceDto.getProducts());
        ReturnInvoiceDto returnInvoiceDto = invoiceServicePort.addInvoice(invoice, products);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnInvoiceDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ReturnInvoiceDto> editInvoice(@RequestBody EditInvoiceDto editInvoiceDto) {
        ReturnInvoiceDto returnInvoiceDto = invoiceServicePort.editInvoice(EditInvoiceDtoMapper.mapToInvoice(editInvoiceDto));
        return ResponseEntity.ok(returnInvoiceDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteInvoice(@PathVariable Long id) {
        invoiceServicePort.deleteInvoice(id);
        return ResponseEntity.ok(id);
    }
}
