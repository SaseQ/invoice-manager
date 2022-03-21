package it.marczuk.invoicemanager.domain.invoice.service;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyServicePort;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.ReturnInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper.ReturnInvoiceDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.allegro.finance.tradukisto.MoneyConverters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepositoryPort invoiceRepositoryPort;
    private final CompanyServicePort companyServicePort;
    private final ProductServicePort productServicePort;

    private static final String INVOICE_ERROR_MESSAGE = "Could not find invoice by id: ";

    public List<ReturnInvoiceDto> getInvoices() {
        List<Invoice> invoices = invoiceRepositoryPort.findAll();
        List<ReturnInvoiceDto> result = new ArrayList<>();
        invoices.forEach(invoice -> {
            List<Product> products = productServicePort.getProductsByInvoice(invoice);
            ReturnInvoiceDto returnInvoiceDto = ReturnInvoiceDtoMapper.mapToReturnInvoiceDto(invoice, products);
            result.add(returnInvoiceDto);
        });
        return result;
    }

    public ReturnInvoiceDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepositoryPort.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(INVOICE_ERROR_MESSAGE + id));
        List<Product> products = productServicePort.getProductsByInvoice(invoice);
        return ReturnInvoiceDtoMapper.mapToReturnInvoiceDto(invoice, products);
    }

    public ReturnInvoiceDto addInvoice(Invoice invoice, List<Product> productList) {
        //set buyer
        Company buyer = companyServicePort.addCompany(invoice.getBuyer());
        invoice.setBuyer(buyer);

        //set seller
        Company seller = companyServicePort.addCompany(invoice.getSeller());
        invoice.setSeller(seller);

        Invoice result = invoiceRepositoryPort.save(invoice);

        productList.forEach(product -> product.setInvoice(result));

        List<Product> finalProducts = productServicePort.addProducts(productList);

        Invoice finalInvoice = invoiceCalculation(result, productList);
        Invoice finalResult = invoiceRepositoryPort.save(finalInvoice);

        return ReturnInvoiceDtoMapper.mapToReturnInvoiceDto(finalResult, finalProducts);
    }

    public ReturnInvoiceDto editInvoice(Invoice invoice) {
        Invoice invoiceEdited = invoiceRepositoryPort.findById(invoice.getId())
                .orElseThrow(() -> new ElementNotFoundException(INVOICE_ERROR_MESSAGE + invoice.getId()));

        Company sellerEdited = companyServicePort.getCompanyById(invoice.getSeller().getId());
        Company buyerEdited = companyServicePort.getCompanyById(invoice.getBuyer().getId());
        List<Product> productsEdited = productServicePort.getProductsByInvoice(invoice);

        invoiceEdited.setPlaceOfIssue(invoice.getPlaceOfIssue());
        invoiceEdited.setDateOfIssue(invoice.getDateOfIssue());
        invoiceEdited.setDatePerformanceOfService(invoice.getDatePerformanceOfService());
        invoiceEdited.setSeller(sellerEdited);
        invoiceEdited.setBuyer(buyerEdited);
        invoiceEdited.setPayType(invoice.getPayType());
        invoiceEdited.setPaymentDeadline(invoice.getPaymentDeadline());

        Invoice finalInvoice = invoiceCalculation(invoiceEdited, productsEdited);
        Invoice result = invoiceRepositoryPort.save(finalInvoice);
        return ReturnInvoiceDtoMapper.mapToReturnInvoiceDto(result, productsEdited);
    }

    public void deleteInvoice(Long id) {
        Invoice invoice = invoiceRepositoryPort.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(INVOICE_ERROR_MESSAGE + id));
        List<Product> products = productServicePort.getProductsByInvoice(invoice);

        products.forEach(product -> productServicePort.deleteProduct(product.getId()));
        invoiceRepositoryPort.delete(invoice);
    }

    private Invoice invoiceCalculation(Invoice invoice, List<Product> products) {
        //set sumToPay
        BigDecimal sumToPay = enumerateSumToPay(products);
        invoice.setSumToPay(sumToPay);

        //set sumToPayAsWords
        String sumToPayAsWords = convertSumToPayAsWords(sumToPay);
        invoice.setSumToPayAsWords(sumToPayAsWords);

        return invoice;
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
