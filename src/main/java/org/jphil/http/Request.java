package org.jphil.http;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class Request {
    private final HttpServletRequest servletRequest;
    private final Map<String, String> pathVariables = new HashMap<>();
    private final Map<String, String> headersMap = new HashMap<>();


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
     * @return url part from the request
     */
    public String pathUrl() {
        return servletRequest.getPathInfo();
    }


    /**
     * @return servlet path from the request
     */
    public String servletPath() {
        return servletRequest.getServletPath();
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
    public String queryString() {
        return servletRequest.getQueryString();
    }


    /**
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
     * @return headers of the request
     */
    public Map<String, String> headers() {
        Enumeration<String> headerNames = servletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = servletRequest.getHeader(key);
            headersMap.put(key, value);
        }
        return headersMap;
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
     * @return map of all form parameters
     */
    public Map<String, String[]> formParams() {
        return servletRequest.getParameterMap();
    }


    /**
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
     * @return return map of all path parameters
     */
    public Map<String, String> pathParams() {
        return pathVariables;
    }


    /**
     * @param name of the cookie
     * @return cookie value of the given cookie name
     */
    public String cookie(String name) {
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
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
        if (!cookieMap.isEmpty()) {
            return cookieMap;
        } else {
            return null;
        }
    }


    /**
     * @return returns the current session associated with this request; if there is no session, it will create one.
     */
    public HttpSession session() {
        return servletRequest.getSession();
    }


    /**
     * @return Returns the session ID specified by the client.
     * This may not be the same as the ID of the current valid session for this request.
     * If the client did not specify a session ID, this method returns null.
     */
    public String requestSessionId() {
        return servletRequest.getRequestedSessionId();
    }

    /**
     * This method changes the session id of the current request and returns the new session id.
     *
     * @return new session id
     */
    public String changeSessionId() {
        return servletRequest.changeSessionId();
    }

    /**
     * isRequestedSessionIdValid?
     *
     * @return true or false
     */
    public boolean isRequestedSessionIdValid() {
        return servletRequest.isRequestedSessionIdValid();
    }


    /**
     * isRequestedSessionIdFromCookie?
     *
     * @return true or false
     */
    public boolean isRequestedSessionIdFromCookie() {
        return servletRequest.isRequestedSessionIdFromCookie();
    }

    /**
     * isRequestedSessionIdFromURL?
     *
     * @return true or false
     */
    public boolean isRequestedSessionIdFromURL() {
        return servletRequest.isRequestedSessionIdFromURL();
    }


    // request body as string
    public String body() {
        byte[] bytes = bodyAsBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }


    // request body as array of bytes
    public byte[] bodyAsBytes() {
        try {
            InputStream byteBody = bodyAsInputStream();
            return IOUtil.toByteArray(byteBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
