package org.jphil.http;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
     * @return the protocol of the request ex: http/https
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
     * @return map of all form parameters
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
     * @return map of all request cookies
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
     * @return returns the current session associated with this request; if there is no session, it will create one.
     */
    public HttpSession session() {
        return servletRequest.getSession();
    }


    /**
     *
     * @return Returns the session ID specified by the client.
     *         This may not be the same as the ID of the current valid session for this request.
     *         If the client did not specify a session ID, this method returns null.
     */
    public String requestSessionId() {
        return servletRequest.getRequestedSessionId();
    }

    /**
     *
     * @return
     */
    public String changeSessionId() {
        return servletRequest.changeSessionId();
    }

    /**
     *
     * @return
     */
    public boolean isRequestedSessionIdValid() {
        return servletRequest.isRequestedSessionIdValid();
    }


    /**
     *
     * @return
     */
    public boolean isRequestedSessionIdFromCookie() {
        return servletRequest.isRequestedSessionIdFromCookie();
    }

    /**
     *
     * @return
     */
    public boolean isRequestedSessionIdFromURL() {
        return servletRequest.isRequestedSessionIdFromURL();
    }


    // request body as string
    public String body() {
        try {
            ServletInputStream inputStream = servletRequest.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder requestBuilder = new StringBuilder();
            String body;
            String line;
            while (!(line = br.readLine()).isBlank()) {
                requestBuilder.append(line + "\r\n");
            }
            System.out.println(requestBuilder);
            body = requestBuilder.toString();
            return body;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }


    // request body as array of bytes
    public byte[] bodyAsBytes()   {

    return null;
    }


    // request body as input stream
    public InputStream bodyAsInputStream() {
        try {
            return servletRequest.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
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
