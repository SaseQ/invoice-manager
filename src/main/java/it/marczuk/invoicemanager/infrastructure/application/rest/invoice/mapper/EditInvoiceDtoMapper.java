package it.marczuk.invoicemanager.infrastructure.application.rest.invoice.mapper;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.mapper.EditCompanyDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.invoice.dto.EditInvoiceDto;

import java.math.BigDecimal;

public class EditInvoiceDtoMapper {

    private static final BigDecimal EMPTY_SUM_TO_PAY = null;
    private static final String EMPTY_SUM_TO_PAY_IN_WORDS = null;

    private EditInvoiceDtoMapper() {
    }

    public static Invoice mapToInvoice(EditInvoiceDto editInvoiceDto) {
        return mapToInvoiceFunction(editInvoiceDto);
    }

    private static Invoice mapToInvoiceFunction(EditInvoiceDto editInvoiceDto) {
        return new Invoice(
                editInvoiceDto.getId(),
                editInvoiceDto.getPlaceOfIssue(),
                editInvoiceDto.getDateOfIssue(),
                editInvoiceDto.getDatePerformanceOfService(),
                EditCompanyDtoMapper.mapToCompany(editInvoiceDto.getSeller()),
                EditCompanyDtoMapper.mapToCompany(editInvoiceDto.getBuyer()),
                editInvoiceDto.getPayType(),
                editInvoiceDto.getPaymentDeadline(),
                EMPTY_SUM_TO_PAY,
                EMPTY_SUM_TO_PAY_IN_WORDS
        );
    }
}
