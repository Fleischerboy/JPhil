package org.jphil.webserver;

import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jphil.servlet.CoreServletFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

public class JettyWebServer {

    private static final Logger logger = LoggerFactory.getLogger(JettyWebServer.class);
    private static int serverPort = 8080;

    private static final Server jettyServer = new Server();

    /**
     * @param port
     **/
    public static void setServerPort(int port) {
        serverPort = port;
    }

    public static void startServer() {
        new Thread(JettyWebServer::initJettyServer).start();
    }

    public static void stopServer() {
        try {
            TimeUnit.SECONDS.sleep(3);
            jettyServer.stop();
            logger.info("WebServer stopped");

        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public static void initJettyServer() {
        ServerConnector serverConnector = new ServerConnector(jettyServer);
        ServletContextHandler ctxHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        serverConnector.setPort(serverPort);
        jettyServer.addConnector(serverConnector);
        jettyServer.setHandler(ctxHandler);
        int port = serverConnector.getPort();
        String info = String.format("http://localhost:%s",port);
        try {
            URL url = new URL(info);
            System.out.println(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ctxHandler.addFilter(CoreServletFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        try {
            jettyServer.start();
            jettyServer.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
