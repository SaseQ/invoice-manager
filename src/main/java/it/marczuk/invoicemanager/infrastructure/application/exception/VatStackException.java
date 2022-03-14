package it.marczuk.invoicemanager.infrastructure.application.exception;

public class VatStackException extends RuntimeException {

    public VatStackException(String message) {
        super(message);
    }
}
