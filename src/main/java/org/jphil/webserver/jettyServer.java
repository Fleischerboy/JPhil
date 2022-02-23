package org.jphil.webserver;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jphil.servlet.CoreServletFilter;

import java.util.EnumSet;

public class jettyServer {


    private static int serverPort;

    private static final Server jettyServer = new Server();


    /**
    * @param port
     **/
    public static void setServerPort(int port) {
        serverPort = port;

    }

    public static void startServer() {
        new Thread(org.jphil.webserver.jettyServer::initJettyServer).start();

    }

 public static void initJettyServer() {
     ServerConnector connector = new ServerConnector(jettyServer);
     ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
     connector.setPort(serverPort);
     jettyServer.addConnector(connector);
     jettyServer.setHandler(contextHandler);
     contextHandler.addFilter(CoreServletFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
     try {
         jettyServer.start();
         jettyServer.join();

     }catch (Exception e) {
         e.printStackTrace();
     }
 }
}
