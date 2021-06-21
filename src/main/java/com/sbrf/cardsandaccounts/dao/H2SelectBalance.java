package com.sbrf.cardsandaccounts.dao;

import com.google.gson.Gson;
import com.sbrf.cardsandaccounts.model.CardBalance;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2SelectBalance {

    private static final String QUERY = "SELECT ACCOUNTNUMBER, BALANCE FROM ACCOUNTBALANCE WHERE ACCOUNTNUMBER = ? ";

    public String selectBalance(BigDecimal accArg) {

        List<CardBalance> balance = new ArrayList<>();
        try (Connection connection = H2JDBCUtils.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setBigDecimal(1, new BigDecimal(String.valueOf(accArg)));

            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) return "Wrong account number!";

                BigDecimal rsAccountNumber = rs.getBigDecimal("accountNumber");
                BigDecimal rsBalance = rs.getBigDecimal("balance");
                CardBalance cardBalance = new CardBalance(rsAccountNumber, rsBalance);
                balance.add(cardBalance);

        } catch (SQLException e) {
            H2JDBCUtils.printSQLException((SQLException) e);
        }
        Gson gson = new Gson();
        String gsonFile = gson.toJson(balance);
        return(gsonFile);
    }
}
