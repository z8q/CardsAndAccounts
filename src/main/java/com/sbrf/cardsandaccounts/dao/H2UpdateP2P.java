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

    public void sendP2P(BigDecimal ACCOUNTNUMBER, BigDecimal balance, BigDecimal ACCOUNTNUMBER2) throws SQLException {
        System.out.println(UPDATE_USERWITHDRAW_SQL);
        System.out.println(UPDATE_USERADDSUM_SQL);
        System.out.println(ACCOUNTNUMBER);
        System.out.println(ACCOUNTNUMBER2);
        System.out.println(balance);

        Connection con = H2JDBCUtils.getConnection();

        try (
        PreparedStatement userWithdraw = con.prepareStatement(UPDATE_USERWITHDRAW_SQL);
        PreparedStatement userAddSum = con.prepareStatement(UPDATE_USERADDSUM_SQL)) {
            con.setAutoCommit(false);

            userWithdraw.setBigDecimal(1, ACCOUNTNUMBER);
            userWithdraw.setBigDecimal(2, balance);
            userWithdraw.setBigDecimal(3, ACCOUNTNUMBER);
            userWithdraw.executeUpdate();

            userAddSum.setBigDecimal(1, ACCOUNTNUMBER2);
            userAddSum.setBigDecimal(2, balance);
            userAddSum.setBigDecimal(3, ACCOUNTNUMBER2);
            userAddSum.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            throw e;
        }
    }
}