package com.example.Booking.exception;

import com.example.Booking.model.ErrorModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorModel> entityNotFoundExceptionHandler(EntityNotFoundException exception) {
        return new ResponseEntity<>(new ErrorModel(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<ErrorModel> httpClientErrorException(HttpClientErrorException exception) {
        return new ResponseEntity<>(new ErrorModel(exception.getLocalizedMessage()), exception.getStatusCode());
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public ResponseEntity<ErrorModel> httpServerErrorException(HttpServerErrorException exception) {
        return new ResponseEntity<>(new ErrorModel(exception.getLocalizedMessage()), exception.getStatusCode());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorModel> runTimeException(RuntimeException exception) {
        return new ResponseEntity<>(new ErrorModel(exception.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
    }
}
