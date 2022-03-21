package it.marczuk.invoicemanager.domain.invoice.service;

import it.marczuk.invoicemanager.domain.company.service.TestCompanyData;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.model.PayType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestInvoiceData {

    private TestInvoiceData() {
    }

    public static Invoice getTestInvoice(Long id) {
        return createTestInvoice(id);
    }

    public static Invoice getEmptyTestInvoice() {
        return createEmptyTestInvoice();
    }

    private static Invoice createTestInvoice(Long id) {
        return new Invoice(
                id,
                "placeOfIssue",
                LocalDate.of(2021, 3, 6),
                LocalDate.of(2021, 3, 7),
                TestCompanyData.getTestCompany(id),
                TestCompanyData.getTestCompany(id+1L),
                PayType.BANK_TRANSFER,
                LocalDate.of(2021, 3, 8),
                BigDecimal.valueOf(59.99),
                "Pisiąt pinć złotych"
        );
    }

    private static Invoice createEmptyTestInvoice() {
        return new Invoice(
                null,
                "placeOfIssue",
                LocalDate.of(2021, 3, 6),
                LocalDate.of(2021, 3, 7),
                null,
                null,
                PayType.BANK_TRANSFER,
                LocalDate.of(2021, 3, 8),
                null,
                null
        );
    }
}
