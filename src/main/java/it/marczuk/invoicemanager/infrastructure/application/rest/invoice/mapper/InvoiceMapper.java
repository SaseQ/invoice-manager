package it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.InvoiceDto;

public class InvoiceMapper {

    private static final Long EMPTY_ID = null;

    private InvoiceMapper() {
    }

    public static Invoice mapToInvoice(InvoiceDto invoiceDto) {
        return mapToInvoiceFunction(invoiceDto);
    }

    private static Invoice mapToInvoiceFunction(InvoiceDto invoiceDto) {
        return new Invoice(
                EMPTY_ID,
                invoiceDto.getPlaceOfIssue(),
                invoiceDto.getDateOfIssue(),
                invoiceDto.getDatePerformanceOfService(),
                invoiceDto.getSeller(),
                invoiceDto.getBuyer(),
                invoiceDto.getProduct(),
                invoiceDto.getPayType(),
                invoiceDto.getPaymentDeadline(),
                invoiceDto.getSumToPay()
        );
    }

}
