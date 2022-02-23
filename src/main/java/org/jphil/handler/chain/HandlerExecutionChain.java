package org.jphil.handler.chain;
import jakarta.servlet.http.HttpServletRequest;



public class HandlerExecutionChain {

    public HandleGet handlerChain;
    private HttpServletRequest request;

    public HandlerExecutionChain(HttpServletRequest request) {
        this.request = request;
        // initialize the chain
        this.handlerChain = new HandleGet();
        HandlePost handlePost = new HandlePost();

        // set the chain of responsibility
        handlerChain.setNextChain(handlePost);
    }











}
