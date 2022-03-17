package org.jphil.http;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class Request {
    private final HttpServletRequest servletRequest;
    private Map<String, String> pathVariables = new HashMap<>();



    /**
     * @param servletRequest
     */
    public Request(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }


    /**
     * @return the protocol of the request eg: http/https
     */
    public String protocol() {
        return servletRequest.getScheme();
    }


    /**
     * @return http method from the request
     */
    public String HttpMethod() {
        return servletRequest.getMethod();
    }


    /**
     * @return url path part from the request
     */
    public String pathUrl() {
        return servletRequest.getPathInfo();
    }

    /**
     * @return the uri without queryString
     */
    public String baseUrl() {
        return servletRequest.getRequestURI();
    }


    /**
     * @return contentType of the request
     */
    public String contentType() {
        return servletRequest.getContentType();
    }


    /**
     * @return the context path
     */
    public String contextPath() {
        return servletRequest.getContextPath();
    }


    /**
     * @return query string
     */
    public String QueryString() {
        return servletRequest.getQueryString();
    }


    public int contentLength() {
        return servletRequest.getContentLength();
    }


    /**
     * @return the ip
     */
    public String ip() {
        return servletRequest.getRemoteAddr();
    }


    /**
     * @return host
     */
    public String host() {
        return servletRequest.getHeader("host");
    }


    /**
     * @param name of the header value
     * @return header value of the given header name
     */
    public String getHeader(String name) {
        return servletRequest.getHeader(name);
    }


    /**
     *
     * @return headers of the request
     */
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



    public String pathParam(String name) {
        String value = pathVariables.get(name);
        if (value == null) {
            System.out.println("this value: " + name + "doesn't exists");
        }
        return value;
    }


    public Map<String, String> getPathVariables() {
        return pathVariables;
    }


    /**
     *
     * @param name of the cookie
     * @return cookie value of the given cookie name
     */
    public Cookie cookie(String name) {
      Cookie[] cookies = servletRequest.getCookies();
      if(cookies != null) {
          for (Cookie cookie: cookies) {
              if(cookie.getName().equals(name)) {
                  return cookie;
              }
          }
      }
      return null;
    }

    /**
     *
     * @return all cookies of the request
     */
    public Map<String, String> cookies() {
        Map<String, String> cookieMap = new HashMap<>();
        Cookie[] cookies = servletRequest.getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            cookieMap.put(cookieName, cookieValue);
        }
        return cookieMap;
    }



    public void addPathVariable(String name, String value) {
        pathVariables.put(name, URLDecoder.decode(value, StandardCharsets.UTF_8));
    }



    public void addPathVariables(Map<String, String> map) {
        for (String key : map.keySet()) {
            String value = map.get(key);
            addPathVariable(key, URLDecoder.decode(value, StandardCharsets.UTF_8));
        }
    }


}
