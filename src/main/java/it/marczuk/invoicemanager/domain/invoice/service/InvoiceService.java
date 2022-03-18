package it.marczuk.invoicemanager.domain.invoice.service;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyServicePort;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.domain.product.port.ProductServicePort;
import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.AddInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.EditInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.ReturnInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper.InvoiceMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper.ReturnInvoiceDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper.AddProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.allegro.finance.tradukisto.MoneyConverters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepositoryPort invoiceRepositoryPort;
    private final CompanyServicePort companyServicePort;
    private final ProductServicePort productServicePort;
    private final EmailNotificationPort emailNotificationPort;

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

    public ReturnInvoiceDto addInvoice(AddInvoiceDto addInvoiceDto) {
        Invoice invoice = InvoiceMapper.mapToInvoice(addInvoiceDto);

        //set buyer
        Company buyer = companyServicePort.addCompany(invoice.getBuyer());
        invoice.setBuyer(buyer);

        //set seller
        Company seller = companyServicePort.addCompany(invoice.getSeller());
        invoice.setSeller(seller);

        Invoice result = invoiceRepositoryPort.save(invoice);

        List<Product> products = addInvoiceDto.getProducts().stream().map(productDto -> {
            Product product = AddProductDtoMapper.mapToProduct(productDto);
            product.setInvoice(result);
            return product;
        }).collect(Collectors.toList());

        List<Product> finalProducts = productServicePort.addProducts(products);

        Invoice finalInvoice = invoiceCalculation(result, products);
        Invoice finalResult = invoiceRepositoryPort.save(finalInvoice);

        emailNotificationPort.send(List.of("saseq.pl@gmail.com"), "Invoice id=" + finalResult.getId() + " was added.");
        return ReturnInvoiceDtoMapper.mapToReturnInvoiceDto(finalResult, finalProducts);
    }

    public ReturnInvoiceDto editInvoice(EditInvoiceDto editInvoiceDto) {
        Invoice invoiceEdited = invoiceRepositoryPort.findById(editInvoiceDto.getId())
                .orElseThrow(() -> new ElementNotFoundException(INVOICE_ERROR_MESSAGE + editInvoiceDto.getId()));

        Company sellerEdited = companyServicePort.getCompanyById(editInvoiceDto.getSellerId());
        Company buyerEdited = companyServicePort.getCompanyById(editInvoiceDto.getBuyerId());
        List<Product> productsEdited = productServicePort.getProductsByInvoice(invoiceEdited);

        invoiceEdited.setPlaceOfIssue(editInvoiceDto.getPlaceOfIssue());
        invoiceEdited.setDateOfIssue(editInvoiceDto.getDateOfIssue());
        invoiceEdited.setDatePerformanceOfService(editInvoiceDto.getDatePerformanceOfService());
        invoiceEdited.setSeller(sellerEdited);
        invoiceEdited.setBuyer(buyerEdited);
        invoiceEdited.setPayType(editInvoiceDto.getPayType());
        invoiceEdited.setPaymentDeadline(editInvoiceDto.getPaymentDeadline());

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
