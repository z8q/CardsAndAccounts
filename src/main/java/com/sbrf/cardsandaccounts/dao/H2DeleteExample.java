package com.sbrf.cardsandaccounts.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DeleteExample {
    private static final String deleteTableSQL = "delete from AccountBalance where id = 1";

    public void deleteRecord() throws SQLException {

        System.out.println(deleteTableSQL);
        try (Connection connection = H2JDBCUtils.getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(deleteTableSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
}