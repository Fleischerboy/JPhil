package org.jphil.servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.handler.HandlerExecution;
import org.jphil.http.Mapping.EndPointMappingFactory;
import org.jphil.http.Mapping.HandlerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.jphil.http.Mapping.EndPointMappingFactory.getHandlerWrapper;
import static org.jphil.utils.PathUtils.extractPathFromRequest;

public class CoreServletFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(CoreServletFilter.class);


    /**
     * @param filterConfig
     * @throws ServletException
     */
    /**
     * When container initializes the Filter, this is the method that gets invoked. This method is called only once in the lifecycle of filter and we should initialize any resources in this method.
     * FilterConfig is used by container to provide init parameters and servlet context object to the Filter. We can throw ServletException in this method.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("CoreServletFilter Init");
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
        String method = request.getMethod();
        String path = extractPathFromRequest(request);
        HandlerWrapper handlerWrapper = EndPointMappingFactory.getHandlerWrapper(method, path, variables);
        HandlerExecution handlerExecution = new HandlerExecution(handlerWrapper);
        if(!(variables.isEmpty())) {
            handlerExecution.setTemplateVariables(variables);
        }
        return handlerExecution;
    }

    @Override
    public void destroy() {

    }
}