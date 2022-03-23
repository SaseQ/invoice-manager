package it.marczuk.invoicemanager.infrastructure.application.rest.product;

import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.AddProductDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.EditProductDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.ReturnProductDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper.AddProductDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper.EditProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice_manager/product")
public class ProductController {

    private final ProductServicePort productServicePort;

    @GetMapping("/get_all")
    public ResponseEntity<List<ReturnProductDto>> getProducts() {
        return ResponseEntity.ok(productServicePort.getProducts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ReturnProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productServicePort.getProductById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ReturnProductDto> addProduct(@RequestBody AddProductDto addProductDto) {
        ReturnProductDto returnProductDto = productServicePort.addProduct(AddProductDtoMapper.mapToProduct(addProductDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(returnProductDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ReturnProductDto> editProduct(@RequestBody EditProductDto editProductDto) {
        ReturnProductDto returnProductDto = productServicePort.editProduct(EditProductDtoMapper.mapToProduct(editProductDto));
        return ResponseEntity.ok(returnProductDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long id) {
        productServicePort.deleteProduct(id);
        return ResponseEntity.ok(id);
    }
}
