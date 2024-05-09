package com.alexbalev.mybank.web;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alexbalev.mybank.model.ErrorMessage;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorMessage handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
    String errors = exception.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
        .collect(Collectors.joining(", "));

    return new ErrorMessage(
        HttpStatus.BAD_REQUEST.value(),
        "Validation failed for one or more fields...",
        "Validation error: " + errors,
        ZonedDateTime.now());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public ErrorMessage handleConstraintViolation(ConstraintViolationException exception) {
    String errors = exception.getConstraintViolations().stream()
        .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
        .collect(Collectors.joining(", "));

    return new ErrorMessage(
        HttpStatus.BAD_REQUEST.value(),
        "One or more contratints were violated...",
        "Validation message: " + errors,
        ZonedDateTime.now());
  }
}
