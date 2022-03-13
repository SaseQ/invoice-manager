package it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.entity;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.infrastructure.persistance.database.company.entity.CompanyEntityMapper;
import it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity.ProductEntityMapper;

import java.util.List;
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

    public static List<InvoiceEntity> mapToInvoiceEntity(List<Invoice> allInvoices) {
        return allInvoices.
                stream()
                .map(InvoiceEntityMapper::mapToInvoiceEntityFunction)
                .collect(Collectors.toList());
    }

    public static InvoiceEntity mapToInvoiceEntity(Invoice invoice) {
        return mapToInvoiceEntityFunction(invoice);
    }

    private static Invoice mapToInvoiceFunction(InvoiceEntity invoiceEntity) {
        return new Invoice(
                invoiceEntity.getId(),
                invoiceEntity.getPlaceOfIssue(),
                invoiceEntity.getDateOfIssue(),
                invoiceEntity.getDatePerformanceOfService(),
                CompanyEntityMapper.mapToCompany(invoiceEntity.getSeller()),
                CompanyEntityMapper.mapToCompany(invoiceEntity.getBuyer()),
                ProductEntityMapper.mapToProduct(invoiceEntity.getProducts()),
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
                ProductEntityMapper.mapToProductEntity(invoice.getProducts()),
                invoice.getPayType(),
                invoice.getPaymentDeadline(),
                invoice.getSumToPay(),
                invoice.getSumToPayAsWords()
        );
    }
}
