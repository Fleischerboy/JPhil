package org.jphil.jphil.core;
import org.jphil.handler.Handler;
import org.jphil.http.HttpMethod;
import org.jphil.webserver.jettyServer;

import static org.jphil.http.routing.EndPointRoutingFactory.*;

public class JPhil {


    private boolean isJettyServerRunning = false;

    public JPhil() {

    }

    /**
     *
     * @param port
     */
    public void start(int port) {
        jettyServer.setServerPort(port);
        jettyServer.startServer();
        isJettyServerRunning = true;

    }



    /**
     * HTTP GET
      @param path
      @param handler
     */
    public void get(String path, Handler handler) {
           addRoute(HttpMethod.GET, path, handler);
    }

    /**
     * HTTP POST
     * @param path
     * @param handler
     */
    public void post(String path, Handler handler) {
        addRoute(HttpMethod.POST, path, handler);
    }


    /**
     * HTTP PUT
     * @param path
     * @param handler
     */
    public void put(String path, Handler handler) {
        addRoute(HttpMethod.PUT, path, handler);
    }

    /**
     * HTTP DELETE
     * @param path
     * @param handler
     */
    public void delete(String path, Handler handler) {
        addRoute(HttpMethod.DELETE, path, handler);
    }


    /**
     * will execute before all request
     * @param handler
     */
    public void before(Handler handler) {
        addBeforeHandler(handler);
    }


    /**
     * will execute before request to /path
     * @param path
     * @param handler
     */
    public void before(String path, Handler handler) {
        addBeforeHandlerWithRoutePath(HttpMethod.BEFORE, path, handler);
    }



    /**
     * After handler that will run after every request.
     * @param handler
     */
    public void after(Handler handler) {
        addAfterHandler(handler);
    }




    /**
     * After handler that will run after request to /path.
     * @param path
     * @param handler
     */
    public void after(String path, Handler handler) {
        addAfterHandlerWithRoutePath(HttpMethod.AFTER, path, handler);
    }




    public void addStaticFilePath(String path) {
        JPhilConfig.setStaticFilePath(path);
    }

    public void addTemplatePath(String path) {
        JPhilConfig.setTemplatePath(path);
    }





    public boolean isJettyServerRunning() {
        return isJettyServerRunning;
    }

}
