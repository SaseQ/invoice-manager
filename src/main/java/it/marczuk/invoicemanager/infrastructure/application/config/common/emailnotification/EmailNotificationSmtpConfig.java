package it.marczuk.invoicemanager.infrastructure.application.config.common.emailnotification;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import it.marczuk.invoicemanager.infrastructure.notification.smtp.email.EmailNotificationSmtpAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailNotificationSmtpConfig {

    @Bean
    public EmailNotificationPort emailNotificationPort() {
        return new EmailNotificationSmtpAdapter();
    }
}
