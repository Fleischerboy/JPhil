package org.jphil.core;
import org.jphil.core.security.AccessManager;
import org.jphil.core.security.RouteRole;
import org.jphil.handler.Handler;
import org.jphil.http.HttpMethod;
import org.jphil.http.Mapping.EndPointMappingFactory;
import org.jphil.http.Mapping.Interceptor.Interceptor;
import org.jphil.http.Mapping.Interceptor.InterceptorFactory;
import org.jphil.webserver.jettyWebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPhil {
    private static final Logger logger = LoggerFactory.getLogger(JPhil.class);
    private static boolean isJettyServerRunning = false;

    private JPhil() {

    }

    public static JPhil startWebServer() {
        JPhil app = new JPhil();
        startJettyServer();
        logger.info("Server started on port 8080");
        return app;
    }

    /**
     * @param port
     * Port number for the server to listen on
     * @return
     * Instance of Application
     */
    public static JPhil startWebServer(int port) {
        JPhil app = new JPhil();
        jettyWebServer.setServerPort(port);
        startJettyServer();
        logger.info("Server started on port " + port);
        return app;
    }

    private static void startJettyServer() {
        jettyWebServer.startServer();
        isJettyServerRunning = true;
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
