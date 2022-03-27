package com.sbrf.cardsandaccounts.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.cardsandaccounts.dao.H2InsertNewCard;
import com.sbrf.cardsandaccounts.dao.H2SelectCardList;
import com.sbrf.cardsandaccounts.model.CreateNewCard;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class HandlerToAddACard implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {
        InputStreamReader isr =  new InputStreamReader(t.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }
        String text = String.valueOf(buf);
        H2SelectCardList h2SelectCardList = new H2SelectCardList();

        try {
            jsonMapper(text);
            String response = "Card is added.\n List of cards: " + h2SelectCardList.selectListOfCards() + "\n";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (SQLException ex) {
            String response = "Card isn't added.\n List of cards: " + h2SelectCardList.selectListOfCards() + "\n";
            t.sendResponseHeaders(404, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            ex.printStackTrace();
        }
        br.close();
        isr.close();
    }

    private void jsonMapper(String json) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        CreateNewCard newCard = mapper.readValue(json, CreateNewCard.class);

        H2InsertNewCard createTableExample = new H2InsertNewCard();
        createTableExample.insertRecord(newCard.getAccountNumber());
    }
}
