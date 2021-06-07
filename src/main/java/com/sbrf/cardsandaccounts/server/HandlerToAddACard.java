package com.sbrf.cardsandaccounts.server;

import com.sbrf.cardsandaccounts.jackson.JacksonObjectMapperAddCardToAccount;
import com.sbrf.cardsandaccounts.dao.H2SelectCardList;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.sql.SQLException;

public class HandlerToAddACard implements HttpHandler {


    @Override
    public void handle(HttpExchange t) throws IOException {

        InputStreamReader isr =  new InputStreamReader(t.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);

        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }
        String text = String.valueOf(buf);
        JacksonObjectMapperAddCardToAccount jacksonObjectMapperAddCardToAccount = new JacksonObjectMapperAddCardToAccount();
        H2SelectCardList h2SelectCardList = new H2SelectCardList();

        try {
            jacksonObjectMapperAddCardToAccount.jsonMapper(text);

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
}
