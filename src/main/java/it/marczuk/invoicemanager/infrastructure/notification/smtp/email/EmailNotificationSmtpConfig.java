package it.marczuk.invoicemanager.infrastructure.notification.smtp.email;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!mem")
public class EmailNotificationSmtpConfig {

    @Bean
    public EmailNotificationPort emailNotificationPort() {
        return new EmailNotificationSmtpAdapter();
    }
}
