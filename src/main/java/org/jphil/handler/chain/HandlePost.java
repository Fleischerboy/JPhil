package org.jphil.handler.chain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HandlePost implements HandlerChain {

    private HandlerChain nextInChain;

    @Override
    public void setNextChain(HandlerChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if(request.getMethod().equals("POST")) {
            System.out.println("handle this post request");
        }
        else {
            System.out.println("Only works for GET and POST");
        }
    }
}
