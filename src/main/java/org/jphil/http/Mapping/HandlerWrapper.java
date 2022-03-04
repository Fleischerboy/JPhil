package org.jphil.http.Mapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.handler.Handler;

public class HandlerWrapper {

    private final Handler handler;

    public HandlerWrapper(Handler handler) {
        this.handler = handler;
    }






    public static void handle(HttpServletRequest request, HttpServletResponse response) {

    }




    @Override
    public String toString() {
        return "HandlerWrapper{" +
                "handler=" + handler +
                '}';
    }
}
