package org.jphil.core;
import org.jphil.handler.Handler;
import org.jphil.http.Method;
import org.jphil.webserver.jettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.jphil.http.routing.EndPointRoutingFactory.*;

public class JPhil {

   Logger logger = LoggerFactory.getLogger(JPhil.class);

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
        logger.info("Server started on port : " + port);
    }



    /**
     * HTTP GET
      @param path
      @param handler
     */
    public void get(String path, Handler handler) {
           addRoute(Method.GET, path, handler);
    }

    /**
     * HTTP POST
     * @param path
     * @param handler
     */
    public void post(String path, Handler handler) {
        addRoute(Method.POST, path, handler);
    }


    /**
     * HTTP PUT
     * @param path
     * @param handler
     */
    public void put(String path, Handler handler) {
        addRoute(Method.PUT, path, handler);
    }

    /**
     * HTTP DELETE
     * @param path
     * @param handler
     */
    public void delete(String path, Handler handler) {
        addRoute(Method.DELETE, path, handler);
    }


    /**
     * will execute before all request
     * @param handler
     */
    public void before(Handler handler) {
       addRoute(Method.BEFORE, "*", handler);
    }


    /**
     * will execute before request to /path
     * @param path
     * @param handler
     */
    public void before(String path, Handler handler) {
         addRoute(Method.BEFORE, path, handler);
    }



    /**
     * After handler that will run after every request.
     * @param handler
     */
    public void after(Handler handler) {
         addRoute(Method.AFTER,"*",handler);
    }




    /**
     * After handler that will run after request to /path.
     * @param path
     * @param handler
     */
    public void after(String path, Handler handler) {
        addRoute(Method.AFTER, path, handler);
    }




    public void setStaticFilePath(String path) {
        JPhilConfig.setStaticFilePath(path);
    }

    public void setTemplatePath(String path) {
        JPhilConfig.setTemplatePath(path);
    }





    public boolean isJettyServerRunning() {
        return isJettyServerRunning;
    }

}
