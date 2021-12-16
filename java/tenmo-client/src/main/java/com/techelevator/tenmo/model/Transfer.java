package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int userIdTo;
    private int UserIdFrom;
    private BigDecimal amount;
    private Account account;
    public int getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(int userIdTo) {
        this.userIdTo = userIdTo;
    }

    public int getUserIdFrom() {
        return UserIdFrom;
    }

    public void setGetUserIdFrom(int getUserIdFrom) {
        this.UserIdFrom = getUserIdFrom;
    }

    public BigDecimal getAmount() {
        if(account != null)
        account.getBalance();
        System.out.println(account.getBalance());

        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


}
