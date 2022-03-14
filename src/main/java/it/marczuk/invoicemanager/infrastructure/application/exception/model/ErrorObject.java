package it.marczuk.invoicemanager.infrastructure.application.exception.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorObject {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;
}
