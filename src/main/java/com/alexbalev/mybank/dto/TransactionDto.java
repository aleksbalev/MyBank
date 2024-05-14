package com.alexbalev.mybank.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TransactionDto {

  @Min(1)
  BigDecimal amount;

  @NotBlank
  String userId;

  String reference;

  String bankSlogan;

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

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getBankSlogan() {
    return bankSlogan;
  }

  public void setBankSlogan(String bankSlogan) {
    this.bankSlogan = bankSlogan;
  }
}
