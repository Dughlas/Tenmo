package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    @JsonProperty("account_id")
    private int accountId;
    @JsonProperty("user_id")
    private int userId;
    private String balance;

    public int getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
