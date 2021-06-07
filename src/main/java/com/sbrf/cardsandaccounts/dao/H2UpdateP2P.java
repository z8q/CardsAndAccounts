package com.sbrf.cardsandaccounts.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2UpdateP2P {

    private static final String UPDATE_USERWITHDRAW_SQL = "UPDATE ACCOUNTBALANCE SET BALANCE = (SELECT BALANCE \n" +
            "FROM ACCOUNTBALANCE WHERE ACCOUNTNUMBER = ?) - ?\n" +
            "WHERE ACCOUNTID=(SELECT ACCOUNTID FROM ACCOUNTBALANCE WHERE ACCOUNTNUMBER = ? );";

    private static final String UPDATE_USERADDSUM_SQL = "UPDATE ACCOUNTBALANCE SET BALANCE = (SELECT BALANCE \n" +
            "FROM ACCOUNTBALANCE WHERE ACCOUNTNUMBER = ?) + ?\n" +
            "WHERE ACCOUNTID=(SELECT ACCOUNTID FROM ACCOUNTBALANCE WHERE ACCOUNTNUMBER = ? );";

    public void sendP2P(BigDecimal ACCOUNTNUMBER, BigDecimal ACCOUNTNUMBER2, BigDecimal balance) throws SQLException {
        System.out.println(UPDATE_USERWITHDRAW_SQL);
        System.out.println(UPDATE_USERADDSUM_SQL);
        System.out.println(ACCOUNTNUMBER);
        System.out.println(ACCOUNTNUMBER2);
        System.out.println(balance);


        /*try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERWITHDRAW_SQL)){
            preparedStatement.setBigDecimal(1, ACCOUNTNUMBER);
            preparedStatement.setBigDecimal(2, balance);
            preparedStatement.setBigDecimal(3, ACCOUNTNUMBER);
            preparedStatement.executeUpdate();

            //int i = preparedStatement.executeUpdate();
            //if (i == 0) {
            //    throw new SQLException();
            //}
        } catch (SQLException e) {
            throw e;
        }
        try (Connection connection2 = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement2 = connection2.prepareStatement(UPDATE_USERADDSUM_SQL)){
            preparedStatement2.setBigDecimal(4, ACCOUNTNUMBER2);
            preparedStatement2.setBigDecimal(5, balance);
            preparedStatement2.setBigDecimal(6, ACCOUNTNUMBER2);
            preparedStatement2.executeUpdate();
            // int z = preparedStatement2.executeUpdate();
            //if (z == 0) {
            //    throw new SQLException();
            //}
        } catch (SQLException e) {
            throw e;
        }*/

        try (Connection connection = H2JDBCUtils.getConnection();
             Connection connection2 = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERWITHDRAW_SQL);
             PreparedStatement preparedStatement2 = connection2.prepareStatement(UPDATE_USERADDSUM_SQL)){
            preparedStatement.setBigDecimal(1, ACCOUNTNUMBER);
            preparedStatement.setBigDecimal(2, balance);
            preparedStatement.setBigDecimal(3, ACCOUNTNUMBER);
            //preparedStatement.executeUpdate();
            preparedStatement2.setBigDecimal(1, ACCOUNTNUMBER2);
            preparedStatement2.setBigDecimal(2, balance);
            preparedStatement2.setBigDecimal(3, ACCOUNTNUMBER2);
            /*preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();*/

            int i = preparedStatement.executeUpdate();
            int z = preparedStatement2.executeUpdate();
            if (i == 0 || z == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw e;
            //H2JDBCUtils.printSQLException(e);
        }





        /*try {
            Connection connection = H2JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERWITHDRAW_SQL);
            PreparedStatement preparedStatement2 = connection.prepareStatement(UPDATE_USERADDSUM_SQL);
            preparedStatement.setBigDecimal(1, ACCOUNTNUMBER);
            preparedStatement.setBigDecimal(2, balance);
            preparedStatement.setBigDecimal(3, ACCOUNTNUMBER);
            //preparedStatement.executeUpdate();
            preparedStatement2.setBigDecimal(1, ACCOUNTNUMBER2);
            preparedStatement2.setBigDecimal(2, balance);
            preparedStatement2.setBigDecimal(3, ACCOUNTNUMBER2);
            preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();

            int i = preparedStatement.executeUpdate();
            int z = preparedStatement2.executeUpdate();
            if (i == 0 || z == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw e;
            //H2JDBCUtils.printSQLException(e);
        }*/

    }
}