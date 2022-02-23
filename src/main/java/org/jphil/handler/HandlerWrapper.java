package org.jphil.handler;
import freemarker.template.TemplateException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.http.request.Request;
import org.jphil.http.response.Response;

import java.io.IOException;

import static org.jphil.http.routing.EndPointRoutingFactory.getHandlerWrapper;

public class HandlerWrapper {

    private final Handler handler;

    public HandlerWrapper(Handler handler) {
        this.handler = handler;
    }


    public static void handle(HttpServletRequest request, HttpServletResponse response) {
        Request req = new Request(request);
        Response res = new Response(response);
        HandlerWrapper handlerWrapper = getHandlerWrapper(request);
        if (handlerWrapper != null) {
            try {
                handlerWrapper.handler.handle(req, res);
            } catch (TemplateException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
            response.setStatus(404);
        }
    }


    @Override
    public String toString() {
        return "HandlerWrapper{" +
                "handler=" + handler +
                '}';
    }
}
