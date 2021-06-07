package com.sbrf.cardsandaccounts.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.cardsandaccounts.model.P2pOperstions;
import com.sbrf.cardsandaccounts.dao.H2UpdateP2P;

import java.io.IOException;
import java.sql.SQLException;


public class JacksonObjectMapperP2P {

    public static void jsonMapper(String json) throws IOException, SQLException {

        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        P2pOperstions p2POperstions = mapper.readValue(json, P2pOperstions.class);
        System.out.println("PDiohndon");

        H2UpdateP2P h2UpdateP2P = new H2UpdateP2P();
        h2UpdateP2P.sendP2P(p2POperstions.getACCOUNTNUMBER(), p2POperstions.getACCOUNTNUMBER2(), p2POperstions.getBALANCE());
        System.out.println("PPOOOOOOOOO");
    }
}