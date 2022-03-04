package org.jphil.webserver;
import org.eclipse.jetty.server.Server;

public class jettyWebServer {

    private static int serverPort = 8080;

    private static Server jettyServer = new Server();

    /**
    * @param port
     **/
    public static void setServerPort(int port) {
        serverPort = port;
    }

    public static void startServer() {

    }

     public static void initJettyServer() {

    }
}
