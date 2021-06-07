package com.sbrf.cardsandaccounts.dao;

import com.sbrf.cardsandaccounts.server.MyHttpServer;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class IntegrationalPostUpdateBalance {

    @Test
    public void shouldUpdateBalance() throws Exception {

        MyHttpServer myHttpServer = new MyHttpServer();
        myHttpServer.startServer();

        H2CreateTablesInsertExamples createTableExample = new H2CreateTablesInsertExamples();
        createTableExample.createTable();

        final URL url = new URL("http://localhost:8080/updateaccountpost");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        String json = "{\n" +
                "    \"accountNumber\": \"40817810400000000001\",\n" +
                "    \"balance\": 700643\n" +
                "}";

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("UTF-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            br.close();

            System.out.println(response.toString());
        }
    }
}