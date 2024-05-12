package com.alexbalev.mybank.web.forms;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AccountForm {

  @NotBlank
  String recievingUserId;

  @Min(1)
  BigDecimal amount;

  String reference;

  public String getRecievingUserId() {
    return recievingUserId;
  }

  public void setRecievingUserId(String recievingUserId) {
    this.recievingUserId = recievingUserId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }
}
