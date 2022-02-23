package org.jphil.handler.chain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.jphil.handler.HandlerWrapper.handle;


public class HandlePost implements HandlerChain {

    private HandlerChain nextInChain;

    @Override
    public void setNextChain(HandlerChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if(request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            handle(request, response);
        }
        else {
            System.out.println("Only works for GET and POST atm");
        }
    }
}
