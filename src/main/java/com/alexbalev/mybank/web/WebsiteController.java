package com.alexbalev.mybank.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.alexbalev.mybank.model.Transaction;
import com.alexbalev.mybank.services.TransactionService;
import com.alexbalev.mybank.web.forms.AccountForm;

import jakarta.validation.Valid;

@Controller
public class WebsiteController {

  private TransactionService transactionService;

  public WebsiteController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("/")
  public String homepage() {
    return "index.html";
  }

  @GetMapping("/account/{userId}")
  public String userTransaction(Model model, @PathVariable("userId") String userId) {
    List<Transaction> transactions = transactionService.findTransactionsByUserId(userId);
    model.addAttribute("transactions", transactions);

    return "transactions.html";
  }

  @GetMapping("/account")
  public String account(Model model) {
    model.addAttribute("accountForm", new AccountForm());
    return "account.html";
  }

  @PostMapping("/account")
  public String account(@ModelAttribute @Valid AccountForm accountForm, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "account.html";
    }

    this.transactionService.create(accountForm.getAmount(), accountForm.getReference(),
        accountForm.getRecievingUserId());

    return "account.html";
  }
}
