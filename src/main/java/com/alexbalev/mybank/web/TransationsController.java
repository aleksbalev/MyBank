package com.alexbalev.mybank.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexbalev.mybank.dto.TransactionDto;
import com.alexbalev.mybank.model.Transaction;
import com.alexbalev.mybank.services.TransactionService;

import jakarta.validation.Valid;

@RestController
public class TransationsController {

  private TransactionService transactionService;

  public TransationsController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("/transactions")
  public List<Transaction> transactions() {
    return transactionService.findAllTransactions();
  }

  @PostMapping("/transactions")
  public Transaction createTransaction(@RequestBody @Valid TransactionDto transactionDto) {
    return transactionService.create(transactionDto.getAmount(), transactionDto.getReference(),
        transactionDto.getUserId());
  }
}
