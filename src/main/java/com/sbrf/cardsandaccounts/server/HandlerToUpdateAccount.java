package com.sbrf.cardsandaccounts.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.cardsandaccounts.dao.H2UpdateBalance;
import com.sbrf.cardsandaccounts.model.CardBalance;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class HandlerToUpdateAccount implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        InputStreamReader isr =  new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }
        String text = String.valueOf(buf);

        try {
            jsonMapper(text);
            String response = "Sum is added to account";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            String response = "Wrong account number!";
            exchange.sendResponseHeaders(404, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        br.close();
        isr.close();
    }

    public void jsonMapper(String json) throws IOException, SQLException {

        ObjectMapper mapper = new ObjectMapper();
        CardBalance cardBalance = mapper.readValue(json, CardBalance.class);

        H2UpdateBalance createTableExample = new H2UpdateBalance();
        createTableExample.updateBalance(cardBalance.getAccountNumber(), cardBalance.getBalance());
    }
}
