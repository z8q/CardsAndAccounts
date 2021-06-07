package com.sbrf.cardsandaccounts.dao;

import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.sql.*;

public class BigUnitListOfCards {

    @Test
    public void shouldAddRightNumberOfCards() throws Exception {

        BigDecimal[] listOfAccounts = new BigDecimal[4];
        BigDecimal defaultAcc = new BigDecimal("40817810400000000001");
        for (int i = 0; i < listOfAccounts.length; i++) {
            listOfAccounts[i] = defaultAcc;
            defaultAcc = defaultAcc.add(new BigDecimal("1"));
        }

        int expected = listOfAccounts.length;

        H2CreateTablesInsertExamples h2CreateTablesInsertExamples = new H2CreateTablesInsertExamples();
        h2CreateTablesInsertExamples.createTable();
        H2InsertNewCard h2InsertNewCard = new H2InsertNewCard();
        for (BigDecimal element : listOfAccounts) {
            h2InsertNewCard.insertRecord(element);
        }
        int actual = 0;

        String query = "SELECT COUNT(*) AS total FROM CARDS;";
        try (Connection connection = H2JDBCUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                actual = rs.getInt("total");
            }
        } catch (SQLException e) {

        }
        System.out.println(" \n ");
        System.out.println("Expected number of cards: " + expected);
        System.out.println("Actual number of cards: " + actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldBeRightJson() throws Exception {

        BigDecimal[] listOfAccounts = new BigDecimal[4];
        BigDecimal defaultAcc = new BigDecimal("40817810400000000001");
        for (int i = 0; i < listOfAccounts.length; i++) {
            listOfAccounts[i] = defaultAcc;
            defaultAcc = defaultAcc.add(new BigDecimal("1"));
        }

        String expected = "[{\"cardId\":1,\"cardNumber\":4276380000000001,\"AccountId\":1},{\"cardId\":2,\"cardNumber\"" +
                ":4276380000000002,\"AccountId\":2},{\"cardId\":3,\"cardNumber\":4276380000000003,\"AccountId\"" +
                ":3},{\"cardId\":4,\"cardNumber\":4276380000000004,\"AccountId\":4}]";

        H2CreateTablesInsertExamples h2CreateTablesInsertExamples = new H2CreateTablesInsertExamples();
        h2CreateTablesInsertExamples.createTable();
        H2InsertNewCard h2InsertNewCard = new H2InsertNewCard();
        for (BigDecimal element : listOfAccounts) {
            h2InsertNewCard.insertRecord(element);
        }
        H2SelectCardList h2SelectCardList = new H2SelectCardList();
        String actual = h2SelectCardList.selectListOfCards();
        System.out.println(" \n ");
        System.out.println(expected);
        System.out.println(actual);


        Assert.assertEquals(expected, actual);
    }
}
