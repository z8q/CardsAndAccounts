package com.sbrf.cardsandaccounts.model;

import java.math.BigDecimal;

public class ListOfCards {

    private int cardId;
    private BigDecimal cardNumber;
    private int AccountId;

    public ListOfCards() {}

    public ListOfCards(int cardId, BigDecimal cardNumber, int AccountId) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.AccountId = AccountId;
    }

    public BigDecimal getCardNumber() {
        return cardNumber;
    }

    public int getCardId() {
        return cardId;
    }

    @Override
    public String toString() {
        return "NumbersOfCards{" +
                "cardId=" + cardId +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
