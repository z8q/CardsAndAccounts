package com.sbrf.cardsandaccounts.server;

import com.sbrf.cardsandaccounts.dao.H2SelectCardList;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class HandlerForCardList implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {

        H2SelectCardList h2SelectCardList = new H2SelectCardList();
        String text = h2SelectCardList.selectListOfCards();

        String response = text;

        t.getResponseHeaders().add("Content-Type", "text/plain; charset=" + StandardCharsets.UTF_8.name());
        t.sendResponseHeaders(200, response.length());
        System.out.println(t.getResponseHeaders().entrySet());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}