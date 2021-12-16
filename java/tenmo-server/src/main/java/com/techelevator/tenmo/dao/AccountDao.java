package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {
    Account findAccountById(int accountId);
    Account findUserById(int userId);
    Account findBalance(String userName);

}
