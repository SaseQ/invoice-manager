package it.marczuk.invoicemanager.domain.product.service;

import it.marczuk.invoicemanager.domain.common.taxclient.TaxClientPort;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositoryPort productRepositoryPort;
    private final TaxClientPort taxClientPort;

    public List<Product> getProducts() {
        return productRepositoryPort.findAll();
    }

    public Product addProduct(Product product) {
        //set NetValue
        BigDecimal netValue = enumerateNetValue(product.getNetPrice(), product.getCount());
        product.setNetValue(netValue);

        //set taxValue
        Integer taxValue = enumerateTaxValue("PL"); //Change countryCode
        product.setTaxValue(taxValue);

        //set taxSum
        double taxConverter = enumerateTaxConverter(taxValue);
        BigDecimal taxSum = enumerateTaxSum(product.getNetValue(), taxConverter);
        product.setTaxSum(taxSum);

        //set grossValue
        BigDecimal grossValue = enumerateGrossValue(product.getNetValue(), taxSum);
        product.setGrossValue(grossValue);

        return productRepositoryPort.save(product);
    }

    public List<Product> addProducts(List<Product> products) {
        products.forEach(product -> {
            //set NetValue
            BigDecimal netValue = enumerateNetValue(product.getNetPrice(), product.getCount());
            product.setNetValue(netValue);

            //set taxValue
            Integer taxValue = enumerateTaxValue("PL"); //Change countryCode
            product.setTaxValue(taxValue);

            //set taxSum
            double taxConverter = enumerateTaxConverter(taxValue);
            BigDecimal taxSum = enumerateTaxSum(product.getNetValue(), taxConverter);
            product.setTaxSum(taxSum);

            //set grossValue
            BigDecimal grossValue = enumerateGrossValue(product.getNetValue(), taxSum);
            product.setGrossValue(grossValue);
        });

        return productRepositoryPort.saveAll(products);
    }

    private BigDecimal enumerateNetValue(BigDecimal netPrice, Integer count) {
        return netPrice.multiply(BigDecimal.valueOf(count));
    }

    private int enumerateTaxValue(String countryCode) {
        return taxClientPort.getTax(countryCode).getStandardRate();
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
