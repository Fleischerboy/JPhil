package org.jphil.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.core.security.RouteRole;
import org.jphil.http.Mapping.AccessManagerWrapper;
import org.jphil.http.Mapping.HandlerWrapper;
import org.jphil.http.Request;
import org.jphil.http.Response;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HandlerExecution {


    private final HandlerWrapper handlerWrapper;


    /**
     * /book/{id} => /book/666 = {id:666}
     */
    private Map<String, String> variables = new HashMap<>();


    private Set<RouteRole> roleSet = new HashSet<>();


    private AccessManagerWrapper accessManagerWrapper;

    public HandlerExecution(HandlerWrapper handlerWrapper) {
        this.handlerWrapper = handlerWrapper;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) {
        invoke(request, response);


    }

    private void invoke(HttpServletRequest request, HttpServletResponse response) {
        Request req = new Request(request);
        Response res = new Response(response);
        if(!(variables.isEmpty())) {
            req.addPathVariables(variables);
        }

        if(!(roleSet.isEmpty())) {
            System.out.println("yes this handler has roles");
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
