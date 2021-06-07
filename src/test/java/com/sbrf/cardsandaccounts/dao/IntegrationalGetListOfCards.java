package com.sbrf.cardsandaccounts.dao;

import com.sbrf.cardsandaccounts.server.MyHttpServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class IntegrationalGetListOfCards {

    @Test
    public void shouldShowListOfCards() throws Exception {


        BigDecimal[] listOfAccounts = new BigDecimal[4];
        BigDecimal defaultAcc = new BigDecimal("40817810400000000001");
        for (int i = 0; i < listOfAccounts.length; i++) {
            listOfAccounts[i] = defaultAcc;
            defaultAcc = defaultAcc.add(new BigDecimal("1"));
        }

        MyHttpServer myHttpServer = new MyHttpServer();
        myHttpServer.startServer();

        H2CreateTablesInsertExamples createTableExample = new H2CreateTablesInsertExamples();
        createTableExample.createTable();

        H2InsertNewCard h2InsertNewCard = new H2InsertNewCard();
        for (BigDecimal element : listOfAccounts) {
            h2InsertNewCard.insertRecord(element);
        }

        String url = "http://localhost:8080/showlistofcardsget";
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

        String expected = "[{\"cardId\":1,\"cardNumber\":4276380000000001,\"AccountId\":1},{\"cardId\":2,\"cardNumber\"" +
                ":4276380000000002,\"AccountId\":2},{\"cardId\":3,\"cardNumber\":4276380000000003,\"AccountId\"" +
                ":3},{\"cardId\":4,\"cardNumber\":4276380000000004,\"AccountId\":4}]";

        Assert.assertEquals(expected, actual);
    }
}
