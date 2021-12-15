package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Account {
    @JsonProperty("account_id")
    private int accountId;
    private int user_id;
    private BigDecimal balance;


    public int getAccountId() {
        return accountId;
    }

    public int getUser_id() {
        return user_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
