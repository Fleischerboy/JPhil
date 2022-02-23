package org.jphil.servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.handler.chain.HandlerExecutionChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;



public class CoreServletFilter implements Filter {
    /** Not part of the framework only commenting atm for my self
     *
     *
     * I use the term middleware, but each language/framework calls the concept differently.
     * NodeJS and Rails calls it middleware. In the Java enterprise world (i.e. Java Servlet),
     * it’s called filters. C# calls it delegate handlers.
     * Essentially, the middleware performs some specific function on the HTTP request or response at a specific stage in the HTTP pipeline before or after the user defined controller.
     * Middleware is a design pattern to eloquently add cross cutting concerns like logging, handling authentication, or gzip compression without having many code contact points.
     *
     * Since these cross-cutting concerns are handled in middleware, the controllers/user defined handlers can focus on the core business logic.
     *
     *
     *
     *
     *
     * A Servlet filter is an object that can intercept HTTP requests targeted at your web application.
     *
     * When the servlet filter is loaded the first time, its init() method is called, just like with servlets.
     *
     * When a HTTP request arrives at your web application which the filter intercepts,
     * the filter can inspect the request URI, the request parameters and the request headers,
     * and based on that decide if it wants to block or forward the request to the target servlet, JSP etc.
     *
     */

    /**
     * @param filterConfig
     * @throws ServletException
     */


    Logger logger = LoggerFactory.getLogger(CoreServletFilter.class);

    /**
     * public interface ServletContext. Defines a set of methods that a servlet uses to communicate with its servlet container,
     * for example, to get the MIME type of a file, dispatch requests, or write to a log file.
     * There is one context per "web application" per Java Virtual Machine.
     */
    private static ServletContext servletContext;


    /**
     * When container initializes the Filter, this is the method that gets invoked. This method is called only once in the lifecycle of filter and we should initialize any resources in this method.
     * FilterConfig is used by container to provide init parameters and servlet context object to the Filter. We can throw ServletException in this method.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("CoreServletFilter Init");

    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Filter is starting...");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // chain of responsibility pattern
        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain(request);
        handlerExecutionChain.handlerChain.handleRequest(request, response);


    }


    @Override
    public void destroy() {

    }

}