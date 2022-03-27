package com.sbrf.cardsandaccounts.dao;

import com.google.gson.Gson;
import com.sbrf.cardsandaccounts.model.ListOfCards;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2SelectCardList {
    private static final String QUERY = "SELECT CardId, CardNumber, AccountId from CARDS";

    public String selectListOfCards() {

        List<ListOfCards> listOfCardsList = new ArrayList<>();

        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        int rsCardId = rs.getInt("CardId");
                        BigDecimal rsCardNumber = rs.getBigDecimal("CardNumber");
                        int rsAccountId = rs.getInt("AccountId");
                        ListOfCards listOfCards = new ListOfCards(rsCardId, rsCardNumber, rsAccountId);
                        listOfCardsList.add(listOfCards);
                    }
            } catch (SQLException e) {
                H2JDBCUtils.printSQLException(e);
        }
        Gson gson = new Gson();
        return gson.toJson(listOfCardsList);
    }

}