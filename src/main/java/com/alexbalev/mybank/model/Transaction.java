package com.alexbalev.mybank.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Transaction {

  private String id;

  private BigDecimal amount;

  private String reference;

  private String bankSlogan;

  private String userId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
  private ZonedDateTime timestamp;

  public Transaction() {
  }

  public Transaction(BigDecimal amount, String reference, ZonedDateTime timestamp, String bankSlogan, String userId) {
    this.id = UUID.randomUUID().toString();
    this.amount = amount;
    this.reference = reference;
    this.timestamp = timestamp;
    this.bankSlogan = bankSlogan;
    this.userId = userId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getReference() {
    return this.reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public ZonedDateTime getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(ZonedDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getBankSlogan() {
    return this.bankSlogan;
  }

  public void setBankSlogan(String bankSlogan) {
    this.bankSlogan = bankSlogan;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
