package com.example.jdbctest.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = { NotFoundUser.class, UpsertException.class, DeleteException.class })
  protected ResponseEntity<Object> handleEntityNotFound(WebRequest request, Exception ex) {
    ErroMessage message = new ErroMessage(
        HttpStatus.NOT_FOUND,
        ex.getMessage(),
        java.time.LocalDateTime.now());

    return handleExceptionInternal(
        ex,
        message,
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST,
        request);
  }
}
