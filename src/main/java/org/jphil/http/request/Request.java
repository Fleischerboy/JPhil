package org.jphil.http.request;


import jakarta.servlet.http.HttpServletRequest;

public class Request {

    HttpServletRequest servletRequest;

    /**
     *
     * @param servletRequest
     */
    public Request(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }


    /**
     * @return http method from the request
     */
    public String getHttpMethod() {
        return servletRequest.getMethod();
    }


    /**
     *
     * @return url path from the request
     */
    public String getPathUrl() {
      return servletRequest.getPathInfo();
    }


    /**
     *
     * @return contentType of the request
     */
    public String getContentType() {
        return servletRequest.getContentType();
    }

    /**
     *
     * @param name
     * @return header value of the given header name
     */
    public String getHeader(String name)  {
        return servletRequest.getHeader(name);
    }







}
