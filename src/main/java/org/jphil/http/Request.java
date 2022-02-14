package org.jphil.http;


import jakarta.servlet.http.HttpServletRequest;

public class Request {

    HttpServletRequest servletRequest;

    public Request(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
}
