package org.jphil.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.util.Jetty;
import org.jphil.core.security.RouteRole;
import org.jphil.handler.HandlerExecution;
import org.jphil.http.Mapping.AccessManagerWrapper;
import org.jphil.http.Mapping.EndPointMappingFactory;
import org.jphil.http.Mapping.HandlerWrapper;
import org.jphil.http.Mapping.Interceptor.Interceptor;
import org.jphil.http.Mapping.Interceptor.InterceptorFactory;
import org.jphil.http.RequestFactory;
import org.jphil.http.ResponseFactory;
import org.jphil.webserver.jettyWebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

import static org.jphil.utils.PathUtils.extractPathFromRequest;
import static org.jphil.webserver.jettyWebServer.stopServer;


public class CoreServletFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(CoreServletFilter.class);

    /**
     *
     * @param filterConfig
     * @throws ServletException
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
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        logger.info("Filter is starting...");
        HandlerExecution handlerExecution = null;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestFactory.create(request);
        ResponseFactory.create(response);
        if (request.getRequestURI().equals("/favicon.ico")) {
            return;
        }
        handlerExecution = getHandler(request);
        try {
            handlerExecution.handle(request, response);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        finally {
            RequestFactory.remove();
            ResponseFactory.remove();
        }


    }

    

    private static HandlerExecution getHandler(HttpServletRequest request) {
        Map<String, String> variables = new HashMap<>();
        Set<RouteRole> roleSet = new HashSet<>();
        String method = request.getMethod();
        String path = extractPathFromRequest(request);
        HandlerWrapper handlerWrapper = EndPointMappingFactory.getHandlerWrapper(method, path, variables, roleSet);
        Stack<HandlerWrapper> beforeInterceptors = InterceptorFactory.getInterceptors(path, Interceptor.BEFORE, variables);
        Stack<HandlerWrapper> afterInterceptors =  InterceptorFactory.getInterceptors(path, Interceptor.AFTER, variables);
        HandlerExecution handlerExecution = new HandlerExecution(beforeInterceptors, handlerWrapper, afterInterceptors);
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