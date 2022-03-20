package it.marczuk.invoicemanager.domain.product.service;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.invoice.service.TestInvoiceData;
import it.marczuk.invoicemanager.domain.product.model.Product;

import java.math.BigDecimal;

public class TestProductData {

    private TestProductData() {
    }

    public static Product getTestProduct(Long id) {
        return createTestProduct(id);
    }

    public static Product getEmptyTestProduct(CountryCode countryCode) {
        return createEmptyTestProduct(countryCode);
    }

    private static Product createTestProduct(Long id) {
        return new Product(
                id,
                "product",
                CountryCode.PL,
                2,
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(10),
                23,
                BigDecimal.valueOf(2.3),
                BigDecimal.valueOf(12.3),
                TestInvoiceData.getTestInvoice(id)
        );
    }

    private static Product createEmptyTestProduct(CountryCode countryCode) {
        return new Product(
                null,
                "product",
                countryCode,
                2,
                BigDecimal.valueOf(5),
                null,
                null,
                null,
                null,
                null
        );
    }
}
