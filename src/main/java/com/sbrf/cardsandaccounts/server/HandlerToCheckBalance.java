package com.sbrf.cardsandaccounts.server;

import com.sbrf.cardsandaccounts.dao.H2SelectBalance;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class HandlerToCheckBalance implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String[] params = exchange.getRequestURI().getQuery().split("=");
        BigDecimal inputAccount = new BigDecimal(String.valueOf(params[1]));
        H2SelectBalance h2SelectBalance = new H2SelectBalance();

        String response = h2SelectBalance.selectBalance(inputAccount);

        exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=" + StandardCharsets.UTF_8.name());
        exchange.sendResponseHeaders(200, response.length());
        System.out.println(exchange.getResponseHeaders().entrySet());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
