package it.marczuk.invoicemanager.domain.common.emailnotification;

import java.util.List;

public interface EmailNotificationPort {

    void send(List<String> recipients, String message);
}
