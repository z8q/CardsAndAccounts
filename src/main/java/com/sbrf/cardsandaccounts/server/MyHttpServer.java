package com.sbrf.cardsandaccounts.server;


import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;


public class MyHttpServer {

    public void startServer() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/createcardpost", new HandlerToAddACard());
        server.createContext("/showlistofcardsget", new HandlerForCardList());
        server.createContext("/updateaccountpost", new HandlerToUpdateAccount());
        server.createContext("/checkbalanceforaccountget", new HandlerToCheckBalance());
        server.createContext("/sendp2p", new HandlerToSendMoney());
        server.setExecutor(null);
        server.start();
    }
}
