package it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.product.model.Product;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.ReturnInvoiceDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.product.mapper.ReturnProductDtoMapper;

import java.util.List;

public class ReturnInvoiceDtoMapper {

    private ReturnInvoiceDtoMapper() {
    }

    public static ReturnInvoiceDto mapToReturnInvoiceDto(Invoice invoice, List<Product> products) {
        return mapToReturnInvoiceDtoFunction(invoice, products);
    }

    private static ReturnInvoiceDto mapToReturnInvoiceDtoFunction(Invoice invoice, List<Product> products) {
        return ReturnInvoiceDto.builder()
                .id(invoice.getId())
                .placeOfIssue(invoice.getPlaceOfIssue())
                .dateOfIssue(invoice.getDateOfIssue())
                .datePerformanceOfService(invoice.getDatePerformanceOfService())
                .seller(invoice.getSeller())
                .buyer(invoice.getBuyer())
                .products(ReturnProductDtoMapper.mapToReturnProductDto(products))
                .payType(invoice.getPayType())
                .paymentDeadline(invoice.getPaymentDeadline())
                .sumToPay(invoice.getSumToPay())
                .sumToPayAsWords(invoice.getSumToPayAsWords())
                .build();
    }
}
