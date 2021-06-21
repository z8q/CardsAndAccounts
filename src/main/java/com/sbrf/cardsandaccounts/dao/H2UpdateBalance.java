package com.sbrf.cardsandaccounts.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2UpdateBalance {

    private static final String UPDATE_USERS_SQL = "UPDATE ACCOUNTBALANCE\n" +
            "SET BALANCE = (SELECT BALANCE\n" +
            "FROM ACCOUNTBALANCE WHERE ACCOUNTNUMBER = ? ) + ? \n" +
            "WHERE ACCOUNTID=(SELECT ACCOUNTID\n" +
            "FROM ACCOUNTBALANCE WHERE ACCOUNTNUMBER = ? );";

    public void updateBalance(BigDecimal addAccount, BigDecimal addFunds) throws SQLException {
        System.out.println(UPDATE_USERS_SQL);

        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setBigDecimal(1, addAccount);
            preparedStatement.setBigDecimal(2, addFunds);
            preparedStatement.setBigDecimal(3, addAccount);

            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}