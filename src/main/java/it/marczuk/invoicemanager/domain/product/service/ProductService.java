package it.marczuk.invoicemanager.domain.product.service;

import it.marczuk.invoicemanager.domain.common.taxclient.TaxClientPort;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import it.marczuk.invoicemanager.infrastructure.application.exception.VatStackException;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.dto.ReturnProductDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper.ReturnProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositoryPort productRepositoryPort;
    private final TaxClientPort taxClientPort;

    private static final String PRODUCT_ERROR_MESSAGE = "Could not find product by id: ";

    public List<ReturnProductDto> getProducts() {
        return ReturnProductDtoMapper.mapToReturnProductDto(productRepositoryPort.findAll());
    }

    public ReturnProductDto getProductById(Long id) {
        Product product = productRepositoryPort.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(PRODUCT_ERROR_MESSAGE + id));
        return ReturnProductDtoMapper.mapToReturnProductDto(product);
    }

    public List<Product> getProductByInvoice(Invoice invoice) {
        return productRepositoryPort.findByInvoice(invoice);
    }

    public ReturnProductDto addProduct(Product product) {
        Product finalProduct = priceCalculations(product);
        return ReturnProductDtoMapper.mapToReturnProductDto(productRepositoryPort.save(finalProduct));
    }

    public List<Product> addProducts(List<Product> products) {
        products.forEach(this::priceCalculations);
        return productRepositoryPort.saveAll(products);
    }

    public ReturnProductDto editProduct(Product product) {
        Product productEdited = productRepositoryPort.findById(product.getId())
                .orElseThrow(() -> new ElementNotFoundException(PRODUCT_ERROR_MESSAGE + product.getId()));

        productEdited.setName(product.getName());

        if(product.getNetPrice().doubleValue() != productEdited.getNetPrice().doubleValue() ||
                product.getCount().intValue() != productEdited.getCount().intValue() ||
                product.getCountry().getAlpha2().equals(productEdited.getCountry().getAlpha2())) {
            Product finalProduct = priceCalculations(product);

            productEdited.setCount(finalProduct.getCount());
            productEdited.setCountry(finalProduct.getCountry());
            productEdited.setNetPrice(finalProduct.getNetPrice());
            productEdited.setNetValue(finalProduct.getNetValue());
            productEdited.setTaxValue(finalProduct.getTaxValue());
            productEdited.setTaxSum(finalProduct.getTaxSum());
            productEdited.setGrossValue(finalProduct.getGrossValue());
        }

        Product result = productRepositoryPort.save(productEdited);
        return ReturnProductDtoMapper.mapToReturnProductDto(result);
    }

    public void deleteProduct(Long id) {
        Product product = productRepositoryPort.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(PRODUCT_ERROR_MESSAGE + id));

        productRepositoryPort.delete(product);
    }

    private Product priceCalculations(Product product) {
        //set NetValue
        BigDecimal netValue = enumerateNetValue(product.getNetPrice(), product.getCount());
        product.setNetValue(netValue);

        //set taxValue
        Integer taxValue = enumerateTaxValue(product.getCountry().getAlpha2());
        product.setTaxValue(taxValue);

        //set taxSum
        double taxConverter = enumerateTaxConverter(taxValue);
        BigDecimal taxSum = enumerateTaxSum(product.getNetValue(), taxConverter);
        product.setTaxSum(taxSum);

        //set grossValue
        BigDecimal grossValue = enumerateGrossValue(product.getNetValue(), taxSum);
        product.setGrossValue(grossValue);

        return product;
    }

    private BigDecimal enumerateNetValue(BigDecimal netPrice, Integer count) {
        return netPrice.multiply(BigDecimal.valueOf(count));
    }

    private int enumerateTaxValue(String countryCode) {
        try {
            return taxClientPort.getTax(countryCode).getStandardRate();
        } catch (VatStackException e) {
            return taxClientPort.getTax("PL").getStandardRate();
        }
    }

    private double enumerateTaxConverter(Integer taxValue) {
        return taxValue * 0.01;
    }

    private BigDecimal enumerateTaxSum(BigDecimal netValue, double taxConverter) {
        return netValue.multiply(BigDecimal.valueOf(taxConverter));
    }

    private BigDecimal enumerateGrossValue(BigDecimal netValue, BigDecimal taxSum) {
        return netValue.add(taxSum);
    }
}
