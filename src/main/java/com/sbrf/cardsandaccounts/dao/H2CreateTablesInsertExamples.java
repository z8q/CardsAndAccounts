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

    public void createTable() throws IOException {

        String accBalPath="src/main/resources/AccountBalance.sql";
        String cardsPath="src/main/resources/Cards.sql";
        String insertIntoAccPath="src/main/resources/InsertIntoAccountBalance.sql";

        Path pathToAccBalPath = Paths.get(accBalPath);
        Path pathToCardsPath = Paths.get(cardsPath);
        Path pathToInsertIntoAccPath = Paths.get(insertIntoAccPath);

        ReadFiles readFiles = new ReadFiles();
        String createTableSQL = readFiles.readFile(String.valueOf(pathToAccBalPath.toAbsolutePath()), StandardCharsets.UTF_8);
        String insertTableSQL = readFiles.readFile(String.valueOf(pathToInsertIntoAccPath.toAbsolutePath()), StandardCharsets.UTF_8);
        String createTableSQL2 = readFiles.readFile(String.valueOf(pathToCardsPath.toAbsolutePath()), StandardCharsets.UTF_8);

        System.out.println("The table is created");
        System.out.println("List of accounts is inserted" + " \n ");
        System.out.println(createTableSQL + " \n ");
        System.out.println(insertTableSQL + " \n ");
        System.out.println(createTableSQL2 + " \n ");

        try (Connection connection1 = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement1 = connection1.prepareStatement(createTableSQL);
             PreparedStatement preparedStatement3 = connection1.prepareStatement(createTableSQL2);) {
            preparedStatement1.execute();
            preparedStatement3.execute();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
        try (Connection connection2 = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement2 = connection2.prepareStatement(insertTableSQL);) {
            preparedStatement2.execute();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
}
