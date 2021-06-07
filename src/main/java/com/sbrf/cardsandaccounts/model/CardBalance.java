package com.sbrf.cardsandaccounts.model;

import java.math.BigDecimal;

public class CardBalance {

    private BigDecimal accountNumber;
    private BigDecimal balance;

    public CardBalance() {}

    public CardBalance(BigDecimal accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public BigDecimal getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
