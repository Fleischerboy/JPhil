package org.jphil.http.request;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class Request {

    HttpServletRequest servletRequest;

    private final Map<String, String> pathVariable = new HashMap<>();

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
        return pathVariable;
    }




    public Map<String, String> getPathVariable() {
        return pathVariable;
    }



    public void addPathVariable(String name, String value) {

    }


    public void addPathVariables(Map<String , String> map){

    }

}
