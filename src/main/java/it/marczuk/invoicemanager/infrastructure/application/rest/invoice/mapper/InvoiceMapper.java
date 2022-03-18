package it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.mapper.AddCompanyDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.AddInvoiceDto;

import java.math.BigDecimal;

public class InvoiceMapper {

    private static final Long EMPTY_ID = null;
    private static final BigDecimal EMPTY_SUM_TO_PAY = null;
    private static final String EMPTY_SUM_TO_PAY_IN_WORDS = null;

    private InvoiceMapper() {
    }

    public static Invoice mapToInvoice(AddInvoiceDto invoiceDto) {
        return mapToInvoiceFunction(invoiceDto);
    }

    private static Invoice mapToInvoiceFunction(AddInvoiceDto invoiceDto) {
        return new Invoice(
                EMPTY_ID,
                invoiceDto.getPlaceOfIssue(),
                invoiceDto.getDateOfIssue(),
                invoiceDto.getDatePerformanceOfService(),
                AddCompanyDtoMapper.mapToCompany(invoiceDto.getSeller()),
                AddCompanyDtoMapper.mapToCompany(invoiceDto.getBuyer()),
                invoiceDto.getPayType(),
                invoiceDto.getPaymentDeadline(),
                EMPTY_SUM_TO_PAY,
                EMPTY_SUM_TO_PAY_IN_WORDS
        );
    }

}
