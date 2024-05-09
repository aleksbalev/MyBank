package com.alexbalev.mybank.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;

public class TransactionDto {

  @Min(1)
  BigDecimal amount;

  String reference;

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
}
