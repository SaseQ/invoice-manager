package it.marczuk.invoicemanager.infrastructure.notification.smtp.email;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class EmailNotificationSmtpAdapter implements EmailNotificationPort {

    @Override
    public void send(List<String> recipients, String message) {
        log.error("SMTP configuration not exist");
    }
}
