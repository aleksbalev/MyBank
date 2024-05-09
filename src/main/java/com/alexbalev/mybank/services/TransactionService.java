package com.alexbalev.mybank.services;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

  public Transaction create(BigDecimal amount, String reference) {
    ZonedDateTime timestamp = ZonedDateTime.now();
    Transaction transaction = new Transaction(amount, reference, timestamp, bankSlogan);
    transactions.add(transaction);
    return transaction;
  }
}
