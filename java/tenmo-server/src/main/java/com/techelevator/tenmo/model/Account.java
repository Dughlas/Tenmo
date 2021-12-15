package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private int accountId;
    private int userid;
    private BigDecimal balance;


    public int getAccountId() {
        return accountId;
    }

    public int getUserid() {
        return userid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}


