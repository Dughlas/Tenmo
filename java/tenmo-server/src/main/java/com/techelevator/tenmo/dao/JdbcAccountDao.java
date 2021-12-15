package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findAccountById(int accountId) {
        String sql = "SELECT account_id FROM accounts  WHERE account_id = ? ";
        return jdbcTemplate.queryForObject(sql, Account.class, accountId);
    }

    @Override
    public Account findUserById(int userId) {
    String sql = "SELECT user_id FROM accounts WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, Account.class, userId);
    }

    @Override
    public String findBalance(String userName) {
        String sql = "SELECT balance FROM accounts JOIN users ON users.user_id = accounts.user_id WHERE username = ? ";
        return jdbcTemplate.queryForObject(sql, String.class, userName);
    }
}
