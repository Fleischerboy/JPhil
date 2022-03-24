package org.jphil.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.core.security.RouteRole;
import org.jphil.handler.HandlerExecution;
import org.jphil.http.Mapping.AccessManagerWrapper;
import org.jphil.http.Mapping.EndPointMappingFactory;
import org.jphil.http.Mapping.HandlerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.jphil.utils.PathUtils.extractPathFromRequest;

public class CoreServletFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(CoreServletFilter.class);
    private static ServletContext servletContext;


    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("CoreServletFilter Init");
        servletContext = filterConfig.getServletContext();
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
        HandlerExecution handlerExecution = null;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (request.getRequestURI().equals("/favicon.ico")) {
            return;
        }

        handlerExecution = getHandler(request);
        try {
            handlerExecution.handle(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }


    }


    private static HandlerExecution getHandler(HttpServletRequest request) {
        Map<String, String> variables = new HashMap<>();
        Set<RouteRole> roleSet = new HashSet<>();
        String method = request.getMethod();
        String path = extractPathFromRequest(request);
        HandlerWrapper handlerWrapper = EndPointMappingFactory.getHandlerWrapper(method, path, variables, roleSet);
        HandlerExecution handlerExecution = new HandlerExecution(handlerWrapper);
        if (!(variables.isEmpty())) {
            handlerExecution.setVariables(variables);
        }
        if (!(roleSet.isEmpty())) {
            handlerExecution.setRoleSet(roleSet);
            AccessManagerWrapper accessManager = EndPointMappingFactory.getAccessManagerWrapper();
            if(accessManager != null) {
                handlerExecution.setAccessManagerWrapper(accessManager);
            }
            else {
                System.out.println("missing implementation of access manager");
            }
        }
        return handlerExecution;
    }

    @Override
    public void destroy() {

    }
}