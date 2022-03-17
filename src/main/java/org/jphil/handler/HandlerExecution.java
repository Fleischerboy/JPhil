package org.jphil.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.http.Mapping.HandlerWrapper;
import org.jphil.http.Request;
import org.jphil.http.Response;

import java.util.HashMap;
import java.util.Map;

public class HandlerExecution {


    private final HandlerWrapper handlerWrapper;


    /**
     * /book/{id} => /book/666 = {id:666}
     */
    private Map<String, String> variables = new HashMap<>();


    public HandlerExecution(HandlerWrapper handlerWrapper) {
        this.handlerWrapper = handlerWrapper;
    }

    public void setTemplateVariables(Map<String, String> templateVariables) {
        this.variables = templateVariables;
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
        handlerWrapper.handle(req, res);

    }

    @Override
    public String toString() {
        return "HandlerExecution{" +
                "handlerWrapper=" + handlerWrapper +
                ", variables=" + variables +
                '}';
    }
}
