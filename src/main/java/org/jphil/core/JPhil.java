package org.jphil.core;

import org.jphil.handler.Handler;
import org.jphil.webserver.jettyServer;

public class JPhil {


    private boolean isRunning = false;

    public JPhil() {

    }



    /**
     *
     * @param port
     */
    public void start(int port) {
        jettyServer.setServerPort(port);
        jettyServer.startServer();
        isRunning = true;

    }

    /**
     * HTTP GET
      @param path
      @param handler
     */
    public void get(String path, Handler handler) {

    }

    /**
     * HTTP POST
     * @param path
     * @param handler
     */

    public void post(String path, Handler handler) {

    }


    /**
     * HTTP PUT
     * @param path
     * @param handler
     */
    public void put(String path, Handler handler) {

    }

    /**
     * HTTP DELETE
     * @param path
     * @param handler
     */
    public void delete(String path, Handler handler) {

    }


    /**
     * will execute before all request
     * @param handler
     */
    public void before(Handler handler) {

    }

    /**
     * will execute before request to /path
     * @param path
     * @param handler
     */
    public void before(String path, Handler handler) {

    }


    /**
     * After handler that will run after every request.
     * @param handler
     */
    public void after(Handler handler) {

    }


    /**
     * After handler that will run after request to /path.
     * @param path
     * @param handler
     */
    public void after(String path, Handler handler) {

    }






    public boolean isRunning() {
        return isRunning;
    }

}
