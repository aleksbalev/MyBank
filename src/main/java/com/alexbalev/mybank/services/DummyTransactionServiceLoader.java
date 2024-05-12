package com.alexbalev.mybank.services;

import java.math.BigDecimal;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
@Profile("dev")
public class DummyTransactionServiceLoader {

  private final TransactionService transactionService;

  public DummyTransactionServiceLoader(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostConstruct
  public void setup() {
    System.out.println("Creating dev invoices...");
    transactionService.create(BigDecimal.valueOf(2.5), "Big Mac Menu", "Logan");
    transactionService.create(BigDecimal.valueOf(5), "Subway", "Logan");
    transactionService.create(BigDecimal.valueOf(10), "Big Tasty Menu", "Peter");
    transactionService.create(BigDecimal.valueOf(33), "Burger King Family Menu", "Peter");
  }
}
