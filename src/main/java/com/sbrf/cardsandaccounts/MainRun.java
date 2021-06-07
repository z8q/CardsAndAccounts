package com.sbrf.cardsandaccounts;

import com.sbrf.cardsandaccounts.dao.H2CreateTablesInsertExamples;
import com.sbrf.cardsandaccounts.server.MyHttpServer;

public class MainRun {

    public static void main(String[] args) throws Exception {

        MyHttpServer myHttpServer = new MyHttpServer();
        myHttpServer.startServer();

        H2CreateTablesInsertExamples createTableExample = new H2CreateTablesInsertExamples();
        createTableExample.createTable();
    }
}
