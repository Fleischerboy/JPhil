package org.jphil.http.Mapping;

import org.jphil.handler.Handler;
import org.jphil.http.Request;
import org.jphil.http.Response;


public class HandlerWrapper {

    private final Handler handler;


    public HandlerWrapper(Handler handler) {
        this.handler = handler;
    }


    public void handle(Request request, Response response) {
        handler.handle(request, response);
    }


    public Handler getHandler() {
        return handler;
    }

    @Override
    public String toString() {
        return String.format("HandlerWrapper{ handler = %s }", handler);
    }


}
