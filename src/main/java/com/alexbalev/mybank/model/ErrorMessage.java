package com.alexbalev.mybank.model;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatusCode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {

  @JsonProperty("status_code")
  private int statusCode;

  private String comment;

  @JsonProperty("error_message")
  private String errorMessage;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
  private ZonedDateTime timestamp;

  public ErrorMessage(int statusCode, String comment, String errorMessage, ZonedDateTime timestamp) {

    this.statusCode = statusCode;
    this.comment = comment;
    this.errorMessage = errorMessage;
    this.timestamp = timestamp;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(ZonedDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
