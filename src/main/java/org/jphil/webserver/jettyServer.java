package org.jphil.webserver;

import org.eclipse.jetty.server.Server;

public class jettyServer {


    private static int serverPort;


    private static Server jettyServer = new Server();

    /**
    * @param port
     **/
    public static void setServerPort(int port) {
        serverPort = port;

    }

    public static void startServer() {

        // logic for init the jetty server

    }
}
