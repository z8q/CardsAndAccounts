package com.sbrf.cardsandaccounts.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2InsertNewCard {

    private static final String INSERT_CARDS_SQL = "INSERT INTO CARDS" +
            " (CARDNUMBER, ACCOUNTID) VALUES " +
            " ( ? , (SELECT ACCOUNTBALANCE.ACCOUNTID " +
            " FROM ACCOUNTBALANCE " +
            " WHERE ACCOUNTBALANCE.ACCOUNTID =" +
            "(SELECT ACCOUNTID FROM ACCOUNTBALANCE " +
            " WHERE ACCOUNTNUMBER = ? )));";

    public void insertRecord(BigDecimal bigDecimal) throws SQLException {
        System.out.println(INSERT_CARDS_SQL);

        CheckCardNumber checkCardNumber = new CheckCardNumber();
        BigDecimal x = null;
        try {
            x = checkCardNumber.checkCardNumber(new BigDecimal("4276380000000001"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARDS_SQL)) {
            preparedStatement.setBigDecimal(1, x);
            preparedStatement.setBigDecimal(2, bigDecimal);
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
