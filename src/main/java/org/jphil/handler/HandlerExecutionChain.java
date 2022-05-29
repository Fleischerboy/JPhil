package org.jphil.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.core.security.RouteRole;
import org.jphil.http.Mapping.AccessManagerWrapper;
import org.jphil.http.Mapping.HandlerWrapper;
import org.jphil.http.Request;
import org.jphil.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class HandlerExecutionChain  {


    private final Stack<HandlerWrapper> beforeInterceptors;
    private final HandlerWrapper handlerWrapper;
    private final Stack<HandlerWrapper> afterInterceptors;
    private String path;

    private static final Logger logger = LoggerFactory.getLogger(HandlerExecutionChain.class);
    /**
     * /book/{id} => /book/666 = {id:666}
     */
    private Map<String, String> variables = new HashMap<>();

    private Set<RouteRole> roleSet = new HashSet<>();

    private AccessManagerWrapper accessManagerWrapper;


    public HandlerExecutionChain(Stack<HandlerWrapper> beforeInterceptors, HandlerWrapper handlerWrapper, Stack<HandlerWrapper> afterInterceptors) {
        this.beforeInterceptors = beforeInterceptors;
        this.handlerWrapper = handlerWrapper;
        this.afterInterceptors = afterInterceptors;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) {
        Request req = new Request(request);
        Response res = new Response(response);
        if (path.equalsIgnoreCase("/stylesheets/style.css")) {
            return;
        }

        if (!(variables.isEmpty())) {
            req.addPathVariables(variables);
        }

        if (beforeInterceptors != null) {
            invokeBeforeInterceptors(req, res);
        }

        if (handlerWrapper != null) {
            invoke(req, res);
        } else {
            logger.warn("Can't find mapping for any endpoints with (GET,POST,PUT OR DELETE) on path: " + path);
            res.statusCode(404);
        }

        if (afterInterceptors != null) {
            invokeAfterInterceptors(req, res);
        }
    }


    private void invokeBeforeInterceptors(Request request, Response response) {
        for (HandlerWrapper oneHandler : beforeInterceptors) {
            oneHandler.handle(request, response);
        }
    }


    private void invoke(Request req, Response res) {
        if (!(roleSet.isEmpty())) {
            if (accessManagerWrapper != null) {
                accessManagerWrapper.manage(handlerWrapper.getHandler(), req, res, roleSet);
            }
        } else {
            handlerWrapper.handle(req, res);
        }
    }


    private void invokeAfterInterceptors(Request req, Response res) {
        for (HandlerWrapper oneHandler : afterInterceptors) {
            oneHandler.handle(req, res);
        }
    }


    public void setVariables(Map<String, String> templateVariables) {
        this.variables = templateVariables;
    }

    public void setRoleSet(Set<RouteRole> roleSet) {
        this.roleSet = roleSet;
    }

    public void setAccessManagerWrapper(AccessManagerWrapper accessManagerWrapper) {
        this.accessManagerWrapper = accessManagerWrapper;
    }

    public HandlerWrapper getHandlerWrapper() {
        return handlerWrapper;
    }

    public AccessManagerWrapper getAccessManagerWrapper() {
        return accessManagerWrapper;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "HandlerExecution{" +
                "beforeInterceptors=" + beforeInterceptors +
                ", handlerWrapper=" + handlerWrapper +
                ", afterInterceptors=" + afterInterceptors +
                ", variables=" + variables +
                ", roleSet=" + roleSet +
                ", accessManagerWrapper=" + accessManagerWrapper +
                '}';
    }
}
