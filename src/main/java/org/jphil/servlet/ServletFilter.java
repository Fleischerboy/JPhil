package org.jphil.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.handler.chain.HandleGet;
import org.jphil.handler.chain.HandlePost;
import org.jphil.handler.chain.HandlerChain;
import org.jphil.handler.chain.HandlerExecutionChain;

import java.io.IOException;

public class ServletFilter implements Filter {
    /** Not part of the framework only commenting
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
     *
     * @param filterConfig
     * @throws ServletException
     */

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // chain of responsibility pattern

        HandlerChain chainRequestGet = new HandleGet();
        HandlerChain chainRequestPost = new HandlePost();
        chainRequestGet.setNextChain(chainRequestPost);
        chainRequestGet.handleRequest(request, response);


        // check if the client request has a match on any of the rout map


    }

    private HandlerExecutionChain getHandler(HttpServletRequest req) {
        return null;
    }


    @Override
    public void destroy() {

    }
}
