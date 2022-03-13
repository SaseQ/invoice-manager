package it.marczuk.invoicemanager.domain.invoice.service;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.model.PayType;
import it.marczuk.invoicemanager.domain.product.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestInvoiceData {

    private static final Long EMPTY_ID = null;

    public static Invoice createTestInvoice() {
        return new Invoice(
                EMPTY_ID,
                "Test Place",
                LocalDate.of(2021, 3, 6),
                LocalDate.of(2021, 3, 7),
                createTestCompany(),
                createTestCompany(),
                List.of(createTestProduct()),
                PayType.BANK_TRANSFER,
                LocalDate.of(2021, 3, 8),
                BigDecimal.valueOf(59.99),
                "Pisiąt pinć złotych"
        );
    }

    private static Company createTestCompany() {
        return new Company(
                EMPTY_ID,
                "Test Company",
                "Test First Name",
                "Test Second Name",
                "1234567890",
                createTestAddress()
        );
    }

    private static Address createTestAddress() {
        return new Address(
                EMPTY_ID,
                "Test Street",
                "1",
                "21-560",
                "Test City",
                CountryCode.getByAlpha4Code("Poland")
        );
    }

    private static Product createTestProduct() {
        return new Product(
                EMPTY_ID,
                "Test Name",
                1,
                BigDecimal.valueOf(59.99),
                BigDecimal.valueOf(59.99),
                23,
                BigDecimal.valueOf(59.99),
                BigDecimal.valueOf(59.99)
        );
    }
}
