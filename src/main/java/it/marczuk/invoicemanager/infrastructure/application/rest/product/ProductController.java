package it.marczuk.invoicemanager.infrastructure.application.rest.product;

import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.AddProductDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.EditProductDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.ReturnProductDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper.AddProductDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper.EditProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice_manager/product")
public class ProductController {

    private final ProductServicePort productServicePort;

    @GetMapping("/get_all")
    public List<ReturnProductDto> getProducts() {
        return productServicePort.getProducts();
    }

    @GetMapping("/get/{id}")
    public ReturnProductDto getProductById(@PathVariable Long id) {
        return productServicePort.getProductById(id);
    }

    @PostMapping("/add")
    public ReturnProductDto addProduct(@RequestBody AddProductDto addProductDto) {
        return productServicePort.addProduct(AddProductDtoMapper.mapToProduct(addProductDto));
    }

    @PutMapping("/update")
    public ReturnProductDto editProduct(@RequestBody EditProductDto editProductDto) {
        return productServicePort.editProduct(EditProductDtoMapper.mapToProduct(editProductDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productServicePort.deleteProduct(id);
    }
}
