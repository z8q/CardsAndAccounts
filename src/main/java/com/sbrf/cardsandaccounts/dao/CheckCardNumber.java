package com.sbrf.cardsandaccounts.dao;

import com.sbrf.cardsandaccounts.model.UniqueCardNumber;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckCardNumber {

    private static final String SELECT_CARDNUMBER_SQL = "SELECT CARDNUMBER FROM CARDS;";
    private static BigDecimal tempBDec = new BigDecimal("0");

    public BigDecimal checkCardNumber(BigDecimal numberToCheck) throws SQLException {

        System.out.println(SELECT_CARDNUMBER_SQL);
        List<UniqueCardNumber> listOfCards = new ArrayList<>();

        try (Connection connection = H2JDBCUtils.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARDNUMBER_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                BigDecimal cardNumberList = rs.getBigDecimal("cardNumber");
                UniqueCardNumber uniqueCardNumber = new UniqueCardNumber(cardNumberList);
                listOfCards.add(uniqueCardNumber);
            }
            int counter = 0;

            for (UniqueCardNumber listOfCard : listOfCards) {
                if (new BigDecimal(String.valueOf(listOfCard)).equals(numberToCheck)) {
                    counter++;
                }
            }
            if (counter > 0) {
                tempBDec = numberToCheck.add(new BigDecimal("1"));
                checkCardNumber(tempBDec);
            } else {
                tempBDec = numberToCheck;
            }
            System.out.println(preparedStatement);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
        return tempBDec;
    }
}
