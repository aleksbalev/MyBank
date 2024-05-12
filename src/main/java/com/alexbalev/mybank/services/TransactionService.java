package com.alexbalev.mybank.services;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alexbalev.mybank.model.Transaction;

@Service
public class TransactionService {

  List<Transaction> transactions = new CopyOnWriteArrayList<>();

  private final String bankSlogan;

  public TransactionService(@Value("${bank.slogan}") String bankSlogan) {
    this.bankSlogan = bankSlogan;
  }

  public List<Transaction> findAllTransactions() {
    return transactions;
  }

  public List<Transaction> findTransactionsByUserId(String userId) {
    return transactions.stream()
        .filter((t) -> t.getUserId().equals(userId))
        .collect(Collectors.toList());
  }

  public Transaction create(BigDecimal amount, String reference, String userId) {
    ZonedDateTime timestamp = ZonedDateTime.now();
    Transaction transaction = new Transaction(amount, reference, timestamp, bankSlogan, userId);
    transactions.add(transaction);
    return transaction;
  }
}
