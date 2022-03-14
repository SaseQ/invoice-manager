package it.marczuk.invoicemanager.infrastructure.application.exception.controller;

import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import it.marczuk.invoicemanager.infrastructure.application.exception.model.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionAdviceController {

    @ResponseBody
    @ExceptionHandler(value = { ElementNotFoundException.class })
    protected ResponseEntity<Object> elementNotFoundHandler(ElementNotFoundException ex) {

        ErrorObject eObject = new ErrorObject();
        eObject.setTimestamp(LocalDateTime.now());
        eObject.setStatus(HttpStatus.NOT_FOUND.value());
        eObject.setMessage(ex.getMessage());

        return new ResponseEntity<>(eObject, HttpStatus.NOT_FOUND);
    }
}
