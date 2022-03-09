package it.marczuk.invoicemanager.infrastructure.notification.inmemory.email;

import it.marczuk.invoicemanager.domain.common.emailnotification.EmailNotificationPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mem")
public class EmailNotificationInMemoryConfig {

    @Bean
    public EmailNotificationPort emailNotificationPort() {
        return new EmailNotificationInMemoryAdapter();
    }
}
