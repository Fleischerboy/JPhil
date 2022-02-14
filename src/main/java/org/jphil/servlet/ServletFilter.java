package org.jphil.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class ServletFilter implements Filter {

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

    }


    @Override
    public void destroy() {

    }
}
