package com.sbrf.cardsandaccounts.dao;

import com.sbrf.cardsandaccounts.server.ReadFiles;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class H2CreateTablesInsertExamples {

    private String createTableSQL;
    private String insertTableSQL;
    private String createTableSQL2;

    public void createTable() throws SQLException, IOException {

        String accBalPath="src/main/resources/AccountBalance.sql";
        String cardsPath="src/main/resources/Cards.sql";
        String insertIntoAccPath="src/main/resources/InsertIntoAccountBalance.sql";

        Path pathToAccBalPath = Paths.get(accBalPath);
        Path pathToCardsPath = Paths.get(cardsPath);
        Path pathToInsertIntoAccPath = Paths.get(insertIntoAccPath);

        ReadFiles readFiles = new ReadFiles();
        String createRequest = readFiles.readFile(String.valueOf(pathToAccBalPath.toAbsolutePath()), StandardCharsets.UTF_8);
        createTableSQL = createRequest;

        String createRequest3 = readFiles.readFile(String.valueOf(pathToInsertIntoAccPath.toAbsolutePath()), StandardCharsets.UTF_8);
        insertTableSQL = createRequest3;

        String createRequest2 = readFiles.readFile(String.valueOf(pathToCardsPath.toAbsolutePath()), StandardCharsets.UTF_8);
        createTableSQL2 = createRequest2;

        String tcMessage = "The table is created";
        String aiMessage = "List of accounts is inserted";

        System.out.println(tcMessage);
        System.out.println(aiMessage + " \n ");
        System.out.println(createTableSQL + " \n ");
        System.out.println(insertTableSQL + " \n ");
        System.out.println(createTableSQL2 + " \n ");

        try (Connection connection1 = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement1 = connection1.prepareStatement(createTableSQL);) {
            preparedStatement1.execute();
        } catch (SQLException e) {

            H2JDBCUtils.printSQLException((SQLException) e);
        }
        try (Connection connection2 = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement2 = connection2.prepareStatement(insertTableSQL);) {
            preparedStatement2.execute();

        } catch (SQLException e) {

            H2JDBCUtils.printSQLException((SQLException) e);
        }
        try (Connection connection3 = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement3 = connection3.prepareStatement(createTableSQL2);) {
            preparedStatement3.execute();
        } catch (SQLException e) {

            H2JDBCUtils.printSQLException((SQLException) e);
        }

        /*try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(createTableSQL);
             PreparedStatement preparedStatement2 = connection.prepareStatement(insertTableSQL);
             PreparedStatement preparedStatement3 = connection.prepareStatement(createTableSQL2)) {
            preparedStatement1.execute();
            preparedStatement1.close();
            preparedStatement2.execute();
            preparedStatement2.close();
            preparedStatement3.execute();
        } catch (SQLException e) {

            H2JDBCUtils.printSQLException((SQLException) e);
        }*/

        /*try (Connection connection = H2JDBCUtils.getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(createTableSQL);
            statement.execute(insertTableSQL);
            statement.execute(createTableSQL2);
        } catch (SQLException e) {

            H2JDBCUtils.printSQLException((SQLException) e);
        }*/
    }
}
