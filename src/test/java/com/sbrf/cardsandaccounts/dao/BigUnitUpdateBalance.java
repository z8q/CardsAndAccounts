package com.sbrf.cardsandaccounts.dao;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class BigUnitUpdateBalance {

    @Test
    public void shouldAddRightSum() throws IOException, SQLException {

        final char dm = (char) 34;
        String expected = "[{" + dm + "accountNumber" + dm + ":40817810400000000001," +
                dm + "balance"  + dm + ":120500}]";

        H2CreateTablesInsertExamples createTableExample = new H2CreateTablesInsertExamples();
        createTableExample.createTable();

        H2UpdateBalance h2UpdateBalance = new H2UpdateBalance();
        h2UpdateBalance.updateBalance(new BigDecimal("40817810400000000001"), new BigDecimal("120500"));

        H2SelectBalance h2SelectBalance = new H2SelectBalance();
        String actual = h2SelectBalance.selectBalance(new BigDecimal("40817810400000000001"));

        Assert.assertEquals(expected,actual);

        System.out.println(" \n\n ");
        System.out.println(expected);
        System.out.println(actual);
    }
}
