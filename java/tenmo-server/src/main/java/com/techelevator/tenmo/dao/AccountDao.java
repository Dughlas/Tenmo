package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {
    Account findAccountById(int accountId);
    Account findUserById(int userId);
    String findBalance(String userName);
}
