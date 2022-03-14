package it.marczuk.invoicemanager.infrastructure.persistance.database.invoice;

import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.entity.InvoiceEntity;
import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.entity.InvoiceEntityMapper;
import it.marczuk.invoicemanager.infrastructure.persistance.database.invoice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class InvoiceDatabaseAdapter implements InvoiceRepositoryPort {

    private final InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findAll() {
        return InvoiceEntityMapper.mapToInvoice(invoiceRepository.findAll());
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return InvoiceEntityMapper.mapToInvoice(invoiceRepository.findById(id));
    }

    @Override
    public Invoice save(Invoice invoice) {
        InvoiceEntity result = invoiceRepository.save(InvoiceEntityMapper.mapToInvoiceEntity(invoice));
        return InvoiceEntityMapper.mapToInvoice(result);
    }
}
