package org.jphil.handler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
