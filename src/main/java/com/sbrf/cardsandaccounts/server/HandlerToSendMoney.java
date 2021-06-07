package com.sbrf.cardsandaccounts.server;

import com.sbrf.cardsandaccounts.jackson.JacksonObjectMapperP2P;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;

public class HandlerToSendMoney implements HttpHandler {

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
        JacksonObjectMapperP2P jacksonObjectMapperP2P = new JacksonObjectMapperP2P();
       // System.out.println(text);

        try {
            jacksonObjectMapperP2P.jsonMapper(text);
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
}
