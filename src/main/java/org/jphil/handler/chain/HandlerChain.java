package org.jphil.handler.chain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface HandlerChain {

    void setNextChain(HandlerChain nextChain);


    void handleRequest(HttpServletRequest request, HttpServletResponse response);
}
