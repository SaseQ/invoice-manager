package it.marczuk.invoicemanager.domain.invoice.service;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyServicePort;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import lombok.RequiredArgsConstructor;
import pl.allegro.finance.tradukisto.MoneyConverters;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepositoryPort invoiceRepositoryPort;
    private final CompanyServicePort companyServicePort;
    private final ProductServicePort productServicePort;
    private final EmailNotificationPort emailNotificationPort;

    public List<Invoice> getInvoices() {
        return invoiceRepositoryPort.findAll();
    }

    public Invoice addInvoice(Invoice invoice) {
        //set buyer
        Company buyer = companyServicePort.addCompany(invoice.getBuyer());
        invoice.setBuyer(buyer);

        //set seller
        Company seller = companyServicePort.addCompany(invoice.getSeller());
        invoice.setSeller(seller);

        //set products
        List<Product> products = productServicePort.addProducts(invoice.getProducts());
        invoice.setProducts(products);

        //set sumToPay
        BigDecimal sumToPay = enumerateSumToPay(products);
        invoice.setSumToPay(sumToPay);

        //set sumToPayAsWords
        String sumToPayAsWords = convertSumToPayAsWords(sumToPay);
        invoice.setSumToPayAsWords(sumToPayAsWords);

        Invoice result = invoiceRepositoryPort.save(invoice);
        emailNotificationPort.send(List.of("saseq.pl@gmail.com"), "Invoice id=" + result.getId() + " was added.");
        return result;
    }

    private BigDecimal enumerateSumToPay(List<Product> products) {
        BigDecimal sumToPay = BigDecimal.ZERO;
        for (Product product : products) {
            sumToPay = sumToPay.add(product.getGrossValue());
        }
        return sumToPay;
    }

    private String convertSumToPayAsWords(BigDecimal sumToPay) {
        MoneyConverters converter = MoneyConverters.POLISH_BANKING_MONEY_VALUE;
        return converter.asWords(sumToPay);
    }
}
