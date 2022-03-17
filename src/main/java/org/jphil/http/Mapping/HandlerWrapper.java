package org.jphil.http.Mapping;
import freemarker.template.TemplateException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.handler.Handler;
import org.jphil.http.Request;
import org.jphil.http.Response;

import java.io.IOException;

public class HandlerWrapper {

    private final Handler handler;

    public HandlerWrapper(Handler handler) {
        this.handler = handler;
    }



    public void handle(Request request, Response response) {
        try {
            handler.handle(request, response);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }

    }


    public Handler getHandler() {
        return handler;
    }

    @Override
    public String toString() {
        return  String.format("HandlerWrapper{ handler = %s }", handler);
    }


}
