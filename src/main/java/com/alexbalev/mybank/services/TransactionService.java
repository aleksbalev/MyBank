package com.alexbalev.mybank.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.Instant;

import com.alexbalev.mybank.model.Transaction;

@Service
public class TransactionService {

  List<Transaction> transactions = new CopyOnWriteArrayList<>();

  private final JdbcTemplate jdbcTemplate;

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final String bankSlogan;

  public TransactionService(@Value("${bank.slogan}") String bankSlogan, JdbcTemplate jdbcTemplate,
      NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.bankSlogan = bankSlogan;
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  public List<Transaction> findAllTransactions() {
    return jdbcTemplate.query("SELECT id, user_id, reference, bank_slogan, amount, timestamp FROM transactions",
        (resultSet, rowNum) -> {
          Transaction transaction = new Transaction();
          transaction.setId(resultSet.getObject("id").toString());
          transaction.setUserId(resultSet.getString("user_id"));
          transaction.setReference(resultSet.getString("reference"));
          transaction.setAmount(BigDecimal.valueOf(resultSet.getInt("amount")));
          transaction.setBankSlogan(resultSet.getString("bank_slogan"));

          Timestamp timestamp = resultSet.getTimestamp("timestamp");
          if (timestamp != null) {
            Instant instant = timestamp.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zonedDateTime = instant.atZone(zoneId);
            transaction.setTimestamp(zonedDateTime);
          }

          return transaction;
        });
  }

  public List<Transaction> findTransactionsByUserId(String userId) {
    String sql = "SELECT id, user_id, reference, bank_slogan, amount, timestamp FROM transactions WHERE user_id = :userId";

    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("userId", userId);

    return namedParameterJdbcTemplate.query(sql, parameters, (resultSet, rowNum) -> {
      Transaction transaction = new Transaction();
      transaction.setId(resultSet.getObject("id").toString());
      transaction.setUserId(resultSet.getString("user_id"));
      transaction.setReference(resultSet.getString("reference"));
      transaction.setAmount(BigDecimal.valueOf(resultSet.getInt("amount")));
      transaction.setBankSlogan(resultSet.getString("bank_slogan"));

      Timestamp timestamp = resultSet.getTimestamp("timestamp");
      if (timestamp != null) {
        Instant instant = timestamp.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        transaction.setTimestamp(zonedDateTime);
      }

      return transaction;
    });
  }

  public Transaction create(BigDecimal amount, String reference, String userId, String bankSlogan) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO transactions (user_id, reference, bank_slogan, amount) VALUES (?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS);

      ps.setString(1, userId);
      ps.setString(2, reference);
      ps.setString(3, bankSlogan);
      ps.setBigDecimal(4, amount);

      return ps;
    }, keyHolder);

    String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString()
        : null;

    Transaction transaction = new Transaction();
    transaction.setId(uuid);
    transaction.setUserId(userId);
    transaction.setBankSlogan(bankSlogan);
    transaction.setReference(reference);
    transaction.setAmount(amount);
    transaction.setTimestamp(ZonedDateTime.now());

    return transaction;
  }
}
