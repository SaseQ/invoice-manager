package it.marczuk.invoicemanager.domain.invoice.adapter;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import it.marczuk.invoicemanager.domain.invoice.service.InvoiceService;
import it.marczuk.invoicemanager.domain.invoice.service.TestInvoiceData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Testowanie całej funkcjonalność
@ExtendWith(MockitoExtension.class)
class InvoiceServiceAdapterTest {

    // given

    // when

    // then

    private final InvoiceRepositoryPort invoiceRepositoryPort = mock(InvoiceRepositoryPort.class);
    private final EmailNotificationPort emailNotificationPort = mock(EmailNotificationPort.class);
    private final InvoiceServiceAdapter invoiceServiceAdapter = new InvoiceServiceAdapter(new InvoiceService(invoiceRepositoryPort, emailNotificationPort));

    @Test
    void shouldAddInvoice() {
        // given
        Invoice invoice = TestInvoiceData.createTestInvoice();
        when(invoiceRepositoryPort.save(any())).then(invocationOnMock -> {
            invoice.setId(1L);
            return invoice;
        });
        // when
        Invoice result = invoiceServiceAdapter.addInvoice(invoice);
        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    void shouldGetInvoices() {
        // given
        Invoice invoice = TestInvoiceData.createTestInvoice();
        when(invoiceRepositoryPort.findAll()).thenAnswer(invocationOnMock -> List.of(invoice));
        invoiceRepositoryPort.save(invoice);
        // when
        List<Invoice> result = invoiceServiceAdapter.getInvoices();
        // then
        assertThat(result).isNotEmpty();
    }

}