package org.jphil.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.core.security.RouteRole;
import org.jphil.http.Mapping.AccessManagerWrapper;
import org.jphil.http.Mapping.HandlerWrapper;
import org.jphil.http.Request;
import org.jphil.http.Response;

import java.util.*;

public class HandlerExecution {


    private Stack<HandlerWrapper> beforeInterceptors;
    private final HandlerWrapper handlerWrapper;
    private Stack<HandlerWrapper> afterInterceptors;
 


    /**
     * /book/{id} => /book/666 = {id:666}
     */
    private Map<String, String> variables = new HashMap<>();


    private Set<RouteRole> roleSet = new HashSet<>();


    private AccessManagerWrapper accessManagerWrapper;

    public HandlerExecution(Stack<HandlerWrapper> beforeInterceptors, HandlerWrapper handlerWrapper, Stack<HandlerWrapper> afterInterceptors) {
        this.beforeInterceptors = beforeInterceptors;
        this.handlerWrapper = handlerWrapper;
        this.afterInterceptors = afterInterceptors;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) {
        if (beforeInterceptors != null) {
            invokeBeforeInterceptors(request, response);

        }

        invoke(request, response);

        if(afterInterceptors != null) {
            invokeAfterInterceptors(request, response);
        }


    }


    private void invokeBeforeInterceptors(HttpServletRequest request, HttpServletResponse response) {
        Request req = new Request(request);
        Response res = new Response(response);
        if(!(beforeInterceptors.isEmpty())) {
            for (HandlerWrapper oneHandler : beforeInterceptors) {
                 oneHandler.handle(req, res);
            }
        }


    }


    private void invokeAfterInterceptors(HttpServletRequest request, HttpServletResponse response) {
        Request req = new Request(request);
        Response res = new Response(response);
        if(!(afterInterceptors.isEmpty())) {
            for (HandlerWrapper oneHandler : afterInterceptors) {
                oneHandler.handle(req, res);
            }
        }

    }




    private void invoke(HttpServletRequest request, HttpServletResponse response) {
        Request req = new Request(request);
        Response res = new Response(response);
        if(!(variables.isEmpty())) {
            req.addPathVariables(variables);
        }
        if(!(roleSet.isEmpty())) {
            if(accessManagerWrapper != null) {
                accessManagerWrapper.manage(handlerWrapper.getHandler(), req ,res, roleSet);
            }
        }
        else {
            handlerWrapper.handle(req, res);
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

    @Override
    public String toString() {
        return "HandlerExecution{" +
                "handlerWrapper=" + handlerWrapper +
                ", variables=" + variables +
                '}';
    }
}
