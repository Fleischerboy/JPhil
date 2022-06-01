package org.jphil.core;

import org.jphil.core.security.AccessManager;
import org.jphil.core.security.RouteRole;
import org.jphil.handler.Handler;
import org.jphil.http.HttpMethod;
import org.jphil.http.Mapping.EndPointMappingFactory;
import org.jphil.http.Mapping.Interceptor.Interceptor;
import org.jphil.http.Mapping.Interceptor.InterceptorMappingFactory;
import org.jphil.webserver.JettyWebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JPhil {
    private static final Logger logger = LoggerFactory.getLogger(JPhil.class);
    private static boolean isWebServerRunning = false;
    private static JPhil instance = null;

    private JPhil() {

    }

    /***
     * @return JPhil instance
     */
    public static JPhil getInstance() {
        if (instance == null) {
            instance = new JPhil();
        }
        return instance;
    }

    /**
     * default port: 8080
     *
     * @return Instance of JPhil
     */
    public static JPhil startWebServer() {
        if (isWebServerRunning) {
            return getInstance();
        } else {
            JPhil app = getInstance();
            startJettyServer();
            logger.info("Server started on port 8080");
            return app;
        }
    }

    /**
     * @param port Port number for the server to listen on
     * @return Instance of JPhil
     */
    public static JPhil startWebServer(int port) {
        if (isWebServerRunning) {
            return getInstance();
        } else {
            JPhil app = getInstance();
            JettyWebServer.setServerPort(port);
            startJettyServer();
            logger.info("Server started on port " + port);
            return app;
        }
    }

    private static void startJettyServer() {
        JettyWebServer.startServer();
        isWebServerRunning = true;
    }

    /**
     * HTTP GET
     * Adds a GET request handler for the specified path.
     *
     * @param path
     * @param handler
     */
    public void get(String path, Handler handler) {
        EndPointMappingFactory.addRoute(HttpMethod.GET, path, handler);
    }

    /**
     * HTTP POST
     * adds a POST request handler for the specified path.
     *
     * @param path
     * @param handler
     */
    public void post(String path, Handler handler) {
        EndPointMappingFactory.addRoute(HttpMethod.POST, path, handler);
    }


    /**
     * HTTP PUT
     * Adds a PUT request handler for the specified path.
     *
     * @param path
     * @param handler
     */
    public void put(String path, Handler handler) {
        EndPointMappingFactory.addRoute(HttpMethod.PUT, path, handler);
    }

    /**
     * HTTP DELETE
     * Adds a DELETE request handler for the specified path.
     *
     * @param path
     * @param handler
     */
    public void delete(String path, Handler handler) {
        EndPointMappingFactory.addRoute(HttpMethod.DELETE, path, handler);
    }

    /**
     * Adds a GET request handler with the given roles for the specified path. Requires an access manager implementation.
     *
     * @param path
     * @param handler
     * @param roles
     */
    public void get(String path, Handler handler, RouteRole... roles) {
        EndPointMappingFactory.addRoute(HttpMethod.GET, path, handler, roles);
    }

    /**
     * Adds a POST request handler with the given roles for the specified path. Requires an access manager implementation.
     *
     * @param path
     * @param handler
     * @param roles
     */
    public void post(String path, Handler handler, RouteRole... roles) {
        EndPointMappingFactory.addRoute(HttpMethod.POST, path, handler, roles);
    }

    /**
     * Adds a PUT request handler with the given roles for the specified, Requires an access manager implementation.
     *
     * @param path
     * @param handler
     * @param roles
     */
    public void put(String path, Handler handler, RouteRole... roles) {
        EndPointMappingFactory.addRoute(HttpMethod.PUT, path, handler, roles);
    }

    /**
     * Adds a DELETE request handler with the given roles for the specified path. Requires an access manager implementation.
     *
     * @param path
     * @param handler
     * @param roles
     */
    public void delete(String path, Handler handler, RouteRole... roles) {
        EndPointMappingFactory.addRoute(HttpMethod.DELETE, path, handler, roles);
    }

    /**
     * Adds a BEFORE request handler for all routes
     * will execute before all request
     *
     * @param handler
     */
    public void before(Handler handler) {
        InterceptorMappingFactory.addInterceptor(Interceptor.BEFORE, "/**", handler);
    }

    /**
     * Adds a BEFORE request handler for the specified path.
     * will execute before request to /path
     *
     * @param path
     * @param handler
     */
    public void before(String path, Handler handler) {
        InterceptorMappingFactory.addInterceptor(Interceptor.BEFORE, path, handler);
    }


    /**
     * Adds an AFTER request handler for the specified path.
     * After handler that will run after request to /path.
     *
     * @param path
     * @param handler
     */
    public void after(String path, Handler handler) {
        InterceptorMappingFactory.addInterceptor(Interceptor.AFTER, path, handler);
    }


    /**
     * Adds an AFTER request handler for all routes in the instance.
     * After handler that will run after every request.
     *
     * @param handler
     */
    public void after(Handler handler) {
        InterceptorMappingFactory.addInterceptor(Interceptor.AFTER, "/**", handler);
    }


    /**
     * Adds a accessManager implementation that will execute before every handler that contains roles.
     *
     * @param accessManager
     */
    public void accessManager(AccessManager accessManager) {
        EndPointMappingFactory.setAccessManager(accessManager);


    }




    /**
     * stop jetty web server
     */
    public void stopWebServer() {
        JettyWebServer.stopServer();
    }


    /**
     * set new path for static files
     *
     * @param path
     */
    public void setStaticFilePath(String path) {
        JPhilConfig.setStaticFilePath(path);
    }


    /**
     * set new path for template files: (FTL)
     *
     * @param path
     */
    public void setTemplatePath(String path) {
        JPhilConfig.setTemplatePath(path);
    }


    /**
     * isJettyServerRunning?
     *
     * @return true or false
     */
    public boolean isJettyServerRunning() {
        return isWebServerRunning;
    }


/*
/*
* this got never implemented, and I don't want to remove it. I might do it in the future.

    public JPhil endPoint(String path) {
        return null;
    }

    public JPhil endPoint(String path, RouteRole... roles) {
        return null;
    }

    public void with(Class<?> cls) {

    }
 */

}
