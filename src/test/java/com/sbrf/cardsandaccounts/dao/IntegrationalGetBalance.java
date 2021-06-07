package com.sbrf.cardsandaccounts.dao;

import com.sbrf.cardsandaccounts.server.MyHttpServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class IntegrationalGetBalance {


    @Test
    public void shouldShowRightBalance() throws Exception {

        MyHttpServer myHttpServer = new MyHttpServer();
        myHttpServer.startServer();

        H2CreateTablesInsertExamples createTableExample = new H2CreateTablesInsertExamples();
        createTableExample.createTable();

        H2UpdateBalance h2UpdateBalance = new H2UpdateBalance();
        h2UpdateBalance.updateBalance(new BigDecimal("40817810400000000001"), new BigDecimal("160500"));

        String url = "http://localhost:8080/checkbalanceforaccountget" +
                "?accountnumber=40817810400000000001";
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine = null;
        StringBuffer nedoActual = new StringBuffer();


        while ((inputLine = in.readLine()) != null) {
            nedoActual.append(inputLine);
        }
        in.close();
        String actual = String.valueOf(nedoActual);

        String expected = "[{\"accountNumber\":40817810400000000001,\"balance\":160500}]";

        Assert.assertEquals(expected, actual);
        }
}

