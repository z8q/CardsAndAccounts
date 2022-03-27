package com.sbrf.cardsandaccounts.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.cardsandaccounts.dao.H2UpdateP2P;
import com.sbrf.cardsandaccounts.model.P2pOperations;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class HandlerToSendMoney implements HttpHandler {

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

        try {
            sendMoney(text);
            String response = "Operation completed";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (SQLException ex) {
            String response = "Operation failed";
            t.sendResponseHeaders(404, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            ex.printStackTrace();
        }
        br.close();
        isr.close();
    }

    private void sendMoney(String json) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        P2pOperations p2POperations = mapper.readValue(json, P2pOperations.class);

        H2UpdateP2P h2UpdateP2P = new H2UpdateP2P();
        h2UpdateP2P.sendP2P(p2POperations.getAccountNumber(), p2POperations.getBalance(), p2POperations.getAccountNumber2());
    }
}
