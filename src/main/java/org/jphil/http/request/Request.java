package org.jphil.http.request;
import jakarta.servlet.http.HttpServletRequest;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class Request {

    HttpServletRequest servletRequest;

    private Map<String, String> pathVariable = new HashMap<>();

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
        return servletRequest.getMethod();
    }


    public String getFullUrl() {
        return servletRequest.getRequestURL().toString();
    }

    /**
     * @return url path from the request
     */
    public String getPathUrl() {
        return servletRequest.getRequestURI();
    }



    /**
     * @return contentType of the request
     */
    public String getContentType() {
        return servletRequest.getContentType();
    }


    /**
     * @return the context path
     */
    public String getContextPath() {
        return servletRequest.getContextPath();
    }


    /**
     *
     * @return query string
     */
    public String getQueryString() {
        return servletRequest.getQueryString();
    }


    /**
     * @return the path part of the request URL.
     */
    public String getPathInfo() {
        return servletRequest.getPathInfo();
    }


    /**
     * @param name
     * @return header value of the given header name
     */
    public String getHeader(String name) {
        return servletRequest.getHeader(name);
    }



    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = servletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = servletRequest.getHeader(name);
            headers.put(name, value);
        }
        return headers;
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
        String value = pathVariable.get(name);
        if(value ==null){
            return null;
        }
        return value;
    }

    public Map<String,String> getPathParams() {
        return pathVariable;
    }



    public String getBody() {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            InputStream inputStream = servletRequest.getInputStream();
            if (inputStream != null) {
                br = new BufferedReader(new InputStreamReader(inputStream));
                char[] cBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = br.read(cBuffer)) > 0) {
                    sb.append(cBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


    public Map<String, String> getPathVariable() {
        return pathVariable;
    }



    public void addPathVariable(String name, String value) {
        pathVariable.put(name, URLDecoder.decode(value, StandardCharsets.UTF_8));
    }


    public void addPathVariables(Map<String , String> map){
        for (String key : map.keySet()){
            String value = map.get(key);
            addPathVariable(key, URLDecoder.decode(value, StandardCharsets.UTF_8));
        }
    }

}
