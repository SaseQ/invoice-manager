package it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.entity;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.infrastructure.persistance.database.company.entity.CompanyEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InvoiceEntityMapper {

    private InvoiceEntityMapper() {
    }

    public static List<Invoice> mapToInvoice(List<InvoiceEntity> allInvoiceEntities) {
        return allInvoiceEntities
                .stream()
                .map(InvoiceEntityMapper::mapToInvoiceFunction)
                .collect(Collectors.toList());
    }

    public static Invoice mapToInvoice(InvoiceEntity invoiceEntity) {
        return mapToInvoiceFunction(invoiceEntity);
    }

    public static InvoiceEntity mapToInvoiceEntity(Invoice invoice) {
        return mapToInvoiceEntityFunction(invoice);
    }

    public static Optional<Invoice> mapToInvoice(Optional<InvoiceEntity> invoiceEntityOptional) {
        return invoiceEntityOptional
                .map(InvoiceEntityMapper::mapToInvoiceFunction);
    }

    private static Invoice mapToInvoiceFunction(InvoiceEntity invoiceEntity) {
        return new Invoice(
                invoiceEntity.getId(),
                invoiceEntity.getPlaceOfIssue(),
                invoiceEntity.getDateOfIssue(),
                invoiceEntity.getDatePerformanceOfService(),
                CompanyEntityMapper.mapToCompany(invoiceEntity.getSeller()),
                CompanyEntityMapper.mapToCompany(invoiceEntity.getBuyer()),
                invoiceEntity.getPayType(),
                invoiceEntity.getPaymentDeadline(),
                invoiceEntity.getSumToPay(),
                invoiceEntity.getSumToPayAsWords()
        );
    }

    private static InvoiceEntity mapToInvoiceEntityFunction(Invoice invoice) {
        return new InvoiceEntity(
                invoice.getId(),
                invoice.getPlaceOfIssue(),
                invoice.getDateOfIssue(),
                invoice.getDatePerformanceOfService(),
                CompanyEntityMapper.mapToCompanyEntity(invoice.getSeller()),
                CompanyEntityMapper.mapToCompanyEntity(invoice.getBuyer()),
                invoice.getPayType(),
                invoice.getPaymentDeadline(),
                invoice.getSumToPay(),
                invoice.getSumToPayAsWords()
        );
    }
}
