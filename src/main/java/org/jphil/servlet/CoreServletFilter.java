package org.jphil.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.core.security.RouteRole;
import org.jphil.handler.HandlerExecutionChain;
import org.jphil.http.ContextThreadLocal;
import org.jphil.http.Mapping.AccessManagerWrapper;
import org.jphil.http.Mapping.EndPointMappingFactory;
import org.jphil.http.Mapping.HandlerWrapper;
import org.jphil.http.Mapping.Interceptor.Interceptor;
import org.jphil.http.Mapping.Interceptor.InterceptorMappingFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

import static org.jphil.utils.PathUtils.extractPathFromRequest;


public class CoreServletFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CoreServletFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("CoreServletFilter Init");

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        logger.info("Filter is starting...");
        HandlerExecutionChain handlerExecutionChain = null;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ContextThreadLocal.create(request);
        ContextThreadLocal.create(response);
        String path = extractPathFromRequest(request);
        if (request.getRequestURI().equals("/favicon.ico")) {
            return;
        }
        handlerExecutionChain = getHandler(request);
        try {
            handlerExecutionChain.handle(request, response);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } finally {
            ContextThreadLocal.removeRequestOnLocalThread();
            ContextThreadLocal.removeResponseOnLocalThread();
        }
    }


    /**
     * @param request
     * @return handlerExecutionChain that can contain different handlers that we want to process for the client.
     */
    private static HandlerExecutionChain getHandler(HttpServletRequest request) {
        Map<String, String> pathVariables = new HashMap<>();
        Set<RouteRole> roleSet = new HashSet<>();
        String method = request.getMethod();
        String path = extractPathFromRequest(request);
        HandlerWrapper handlerWrapper = EndPointMappingFactory.getHandlerWrapper(method, path, pathVariables, roleSet);
        Stack<HandlerWrapper> beforeInterceptors = InterceptorMappingFactory.getInterceptors(path, Interceptor.BEFORE, pathVariables);
        Stack<HandlerWrapper> afterInterceptors = InterceptorMappingFactory.getInterceptors(path, Interceptor.AFTER, pathVariables);
        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain(beforeInterceptors, handlerWrapper, afterInterceptors);
        if (!(pathVariables.isEmpty())) {
            handlerExecutionChain.setVariables(pathVariables);
        }
        if (!(roleSet.isEmpty())) {
            handlerExecutionChain.setRoleSet(roleSet);
            AccessManagerWrapper accessManager = EndPointMappingFactory.getAccessManagerWrapper();
            if (accessManager != null) {
                handlerExecutionChain.setAccessManagerWrapper(accessManager);
            } else {
                System.out.println("missing implementation of access manager");
            }
        }
        handlerExecutionChain.setPath(path);
        return handlerExecutionChain;
    }

    @Override
    public void destroy() {

    }
}