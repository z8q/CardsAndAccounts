package com.sbrf.cardsandaccounts.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.cardsandaccounts.model.CardBalance;
import com.sbrf.cardsandaccounts.dao.H2UpdateBalance;

import java.io.IOException;
import java.sql.SQLException;

public class JacksonObjectMapperUpdateBalance {

    public static void jsonMapper(String json) throws IOException, SQLException {

        ObjectMapper mapper = new ObjectMapper();
        CardBalance cardBalance = mapper.readValue(json, CardBalance.class);

        H2UpdateBalance createTableExample = new H2UpdateBalance();
        createTableExample.updateBalance(cardBalance.getAccountNumber(), cardBalance.getBalance());


    }
}