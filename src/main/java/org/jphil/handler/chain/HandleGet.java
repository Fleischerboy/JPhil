package org.jphil.handler.chain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HandleGet implements HandlerChain {

    private HandlerChain nextInChain;

    @Override
    public void setNextChain(HandlerChain nextChain) {
        this.nextInChain = nextChain;

    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if(request.getMethod().equals("GET")) {
            System.out.println("handle this get request");
        }
        else {
            nextInChain.handleRequest(request, response);
        }
    }
}
