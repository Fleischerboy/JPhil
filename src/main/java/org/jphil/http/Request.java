package org.jphil.http;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;


public class Request {

   private final HttpServletRequest servletRequest;
   private final Map<String, String> pathVariables = new HashMap<>();

    /**
     * @param servletRequest
     */
    public Request(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    /**
     * @return http method from the request
     */
    public String getHttpMethod() {
        return null;
    }


    public String getFullUrl() {
        return null;
    }

    /**
     * @return url path from the request
     */
    public String getPathUrl() {
        return null;
    }

    /**
     * @return contentType of the request
     */
    public String getContentType() {
        return null;
    }


    /**
     * @return the context path
     */
    public String getContextPath() {
        return null;
    }


    /**
     *
     * @return query string
     */
    public String getQueryString() {
        return null;
    }


    /**
     * @return the path part of the request URL.
     */
    public String getPathInfo() {
        return null;
    }


    /**
     * @param name
     * Name of the header value
     * @return header value of the given header name
     */
    public String getHeader(String name) {
        return null;
    }

    public Map<String, String> getHeaders() {
     return null;
    }

    /*
     * @param name
     * @return
     */
    public String getFormParam(String name) {
        if (name.isEmpty()) {
            return null;
        }
        return servletRequest.getParameter(name);
    }

    public Map<String, String[]> getFormData() {
        return servletRequest.getParameterMap();
    }

    public String getPathParam(String name) {
    return null;
    }

    public Map<String,String> getPathParams() {
        return pathVariables;
    }

    public Map<String, String> getPathVariable() {
        return pathVariables;
    }

    public String getCookie(String role) {
        return null;
    }

}
