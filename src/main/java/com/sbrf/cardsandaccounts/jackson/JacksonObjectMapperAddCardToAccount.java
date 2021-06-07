package com.sbrf.cardsandaccounts.jackson;

import java.io.IOException;
import java.sql.SQLException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.cardsandaccounts.model.CreateNewCard;
import com.sbrf.cardsandaccounts.dao.H2InsertNewCard;


public class JacksonObjectMapperAddCardToAccount {

    public static void jsonMapper(String json) throws IOException, SQLException {

        ObjectMapper mapper = new ObjectMapper();
        CreateNewCard newCard = mapper.readValue(json, CreateNewCard.class);

        H2InsertNewCard createTableExample = new H2InsertNewCard();
        createTableExample.insertRecord(newCard.getAccountNumber());
    }
}