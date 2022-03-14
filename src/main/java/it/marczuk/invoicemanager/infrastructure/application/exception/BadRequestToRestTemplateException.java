package it.marczuk.invoicemanager.infrastructure.application.exception;

public class BadRequestToRestTemplateException extends RuntimeException {

    public BadRequestToRestTemplateException(String url, String errorStatus) {
        super("Error request to: " + url + ", Error status: " + errorStatus);
    }
}
