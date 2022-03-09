package it.marczuk.invoicemanager.domain.invoice.service;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import it.marczuk.invoicemanager.domain.invoice.model.Invoice;
import it.marczuk.invoicemanager.domain.invoice.port.InvoiceRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

//Testowanie servisu
@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private EmailNotificationPort emailNotificationPort;
    @Mock
    private InvoiceRepositoryPort invoiceRepositoryPort;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    void shouldAddInvoice() {
        // given
        Invoice invoice = TestInvoiceData.createTestInvoice();
        when(invoiceRepositoryPort.save(any())).then(invocationOnMock -> {
            invoice.setId(1L);
            return invoice;
        });
        // when
        Invoice result = invoiceService.addInvoice(invoice);
        // then
        assertThat(result.getId()).isEqualTo(1L);
    }

}