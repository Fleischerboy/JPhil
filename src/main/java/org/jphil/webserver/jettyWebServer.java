package org.jphil.webserver;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jphil.servlet.CoreServletFilter;

import java.util.EnumSet;

public class jettyWebServer {



    private static int serverPort = 8080;

    private static final Server jettyServer = new Server();

    /**
    * @param port
     **/
    public static void setServerPort(int port) {
        serverPort = port;
    }

    public static void startServer() {
        new Thread(org.jphil.webserver.jettyWebServer::initJettyServer).start();
    }



     public static void initJettyServer() {
         ServerConnector serverConnector = new ServerConnector(jettyServer);
         ServletContextHandler ctxHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
         serverConnector.setPort(serverPort);
         jettyServer.addConnector(serverConnector);
         jettyServer.setHandler(ctxHandler);
         ctxHandler.addFilter(CoreServletFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
         try {
             jettyServer.start();
             jettyServer.join();

         }catch (Exception e) {
             e.printStackTrace();
         }
     }


    public static void stopServer(){
        try {
            jettyServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
