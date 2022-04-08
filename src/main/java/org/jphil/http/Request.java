package org.jphil.http;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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


    /**
     *
     * @return content length
     */
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
    public String header(String name) {
        return servletRequest.getHeader(name);
    }


    /**
     *
     * @return headers of the request
     */
    public Map<String, String> headers() {
        return null;
    }



    /*
     * @param name
     * @return form parameter by name, as string
     */
    public String formParam(String name) {
        if (name.isEmpty()) {
            return null;
        }
        return servletRequest.getParameter(name);
    }


    /**
     *
     * @return map
     */
    public Map<String, String[]> formParams() {
        return servletRequest.getParameterMap();
    }


    /**
     *
     * @param name
     * @return path parameter by name as string
     */
    public String pathParam(String name) {
        String value = pathVariables.get(name);
        if (value == null) {
            System.out.println("this value: " + name + "doesn't exists");
        }
        return value;
    }

    /**
     *
     * @return return map of all path parameters
     */
    public Map<String, String> pathParams() {
        return pathVariables;
    }


    /**
     *
     * @param name of the cookie
     * @return cookie value of the given cookie name
     */
    public String cookie(String name) {
      Cookie[] cookies = servletRequest.getCookies();
      if(cookies != null) {
          for (Cookie cookie: cookies) {
              if(cookie.getName().equals(name)) {
                  return cookie.getValue();
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


    /**
     *
     * @return returns current session related to this request. and if the request does not have a session, it will create one.
     */
    public HttpSession session() {
        return servletRequest.getSession();
    }



    public String requestSessionId() {
        return servletRequest.getRequestedSessionId();
    }


    public String changeSessionId() {
        return servletRequest.changeSessionId();
    }


    public boolean isRequestedSessionIdValid() {
        return servletRequest.isRequestedSessionIdValid();
    }


    public boolean isRequestedSessionIdFromCookie() {
        return servletRequest.isRequestedSessionIdFromCookie();
    }


    public boolean isRequestedSessionIdFromURL() {
        return servletRequest.isRequestedSessionIdFromURL();
    }


    public RequestDispatcher getRequestDispatcher(String resource) {
        return servletRequest.getRequestDispatcher(resource);
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
