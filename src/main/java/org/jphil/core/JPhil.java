package org.jphil.core;
import org.jphil.core.security.AccessManager;
import org.jphil.core.security.RouteRole;
import org.jphil.handler.Handler;
import org.jphil.http.HttpMethod;
import org.jphil.http.Mapping.EndPointMappingFactory;
import org.jphil.http.Mapping.Interceptor.Interceptor;
import org.jphil.http.Mapping.Interceptor.InterceptorFactory;
import org.jphil.webserver.JettyWebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class JPhil {
    private static final Logger logger = LoggerFactory.getLogger(JPhil.class);
    private static boolean isWebServerRunning = false;
    private static JPhil instance = null;

    private JPhil() {

    }

    /**
     *
     * @return JPhil instance
     */
    public static JPhil getInstance() {
        if(instance == null) {
            instance = new JPhil();
        }
        return instance;
    }

    public static JPhil startWebServer() {
        if(isWebServerRunning) {
            return getInstance();
        }
        else {
            JPhil app = getInstance();
            startJettyServer();
            logger.info("Server started on port 8080");
            return app;
        }
    }

    /**
     * @param port
     * Port number for the server to listen on
     * @return
     * Instance of Application
     */
    public static JPhil startWebServer(int port) {
        if(isWebServerRunning) {
            return getInstance();
        }
        else {
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
      @param path
      @param handler
     */
    public void get(String path, Handler handler) {
        EndPointMappingFactory.addRoute(HttpMethod.GET, path, handler);
    }

    /**
     * HTTP POST
     * @param path
     * @param handler
     */
    public void post(String path, Handler handler) {
        EndPointMappingFactory.addRoute(HttpMethod.POST, path, handler);
    }


    /**
     * HTTP PUT
     * @param path
     * @param handler
     */
    public void put(String path, Handler handler) {
        EndPointMappingFactory.addRoute(HttpMethod.PUT, path, handler);
    }

    /**
     * HTTP DELETE
     * @param path
     * @param handler
     */
    public void delete(String path, Handler handler) {
        EndPointMappingFactory.addRoute(HttpMethod.DELETE, path, handler);
    }

    /**
     *
     * @param path
     * @param handler
     * @param roles
     */
    public void get(String path, Handler handler, RouteRole... roles) {
        EndPointMappingFactory.addRoute(HttpMethod.GET, path, handler, roles);
    }

    /**
     *
     * @param path
     * @param handler
     * @param roles
     */
    public void post(String path, Handler handler, RouteRole... roles) {
        EndPointMappingFactory.addRoute(HttpMethod.POST, path, handler, roles);
    }

    /**
     *
     * @param path
     * @param handler
     * @param roles
     */
    public void put(String path, Handler handler, RouteRole... roles) {
        EndPointMappingFactory.addRoute(HttpMethod.PUT, path, handler, roles);
    }

    /**
     *
     * @param path
     * @param handler
     * @param roles
     */
    public void delete(String path, Handler handler, RouteRole... roles) {
        EndPointMappingFactory.addRoute(HttpMethod.DELETE, path, handler, roles);
    }

    /**
     * will execute before all request
     * @param handler
     */
    public void before(Handler handler) {
        InterceptorFactory.addInterceptor(Interceptor.BEFORE, "/**", handler);
    }

    /**
     * will execute before request to /path
     * @param path
     * @param handler
     */
    public void before(String path, Handler handler) {
        InterceptorFactory.addInterceptor(Interceptor.BEFORE, path, handler);
    }


    /**
     * After handler that will run after request to /path.
     * @param path
     * @param handler
     */
    public void after(String path, Handler handler) {
        InterceptorFactory.addInterceptor(Interceptor.AFTER, path, handler);
    }


    /**
     * After handler that will run after every request.
     * @param handler
     */
    public void after(Handler handler) {
        InterceptorFactory.addInterceptor(Interceptor.AFTER, "/**", handler);
    }


    /**
     *
     * @param accessManager
     */
    public void accessManager(AccessManager accessManager) {
        EndPointMappingFactory.setAccessManager(accessManager);


    }




    public JPhil endPoint(String path) {
       return null;
    }

    public JPhil endPoint(String path, RouteRole... roles) {
        return null;
    }

    public void with(Class<?> cls) {

    }

    /**
     * stop jetty web server
     */
    public void stopWebServer(){
        JettyWebServer.stopServer();
    }


    /**
     *  set new path for static files
     * @param path
     */
    public void setStaticFilePath(String path) {
        JPhilConfig.setStaticFilePath(path);
    }

    /**
     * set new path for template files: (FTL)
     * @param path
     */
    public void setTemplatePath(String path) {
        JPhilConfig.setTemplatePath(path);
    }

    /**
     *
     * @return isJettyServerRunning?
     */
    public boolean isJettyServerRunning() {
        return isWebServerRunning;
    }

}
