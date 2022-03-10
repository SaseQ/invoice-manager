package it.marczuk.invoicemanager.infrastructure.application.rest.product;

import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.AddProductDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper.AddProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice_manager/product")
public class ProductController {

    private final ProductServicePort productServicePort;

    @GetMapping("/get_all")
    public List<Product> getProducts() {
        return productServicePort.getProducts();
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody AddProductDto addProductDto) {
        return productServicePort.addProduct(AddProductDtoMapper.mapToProduct(addProductDto));
    }
}
