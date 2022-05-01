package org.jphil.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.jphil.handler.Handler;

import java.net.URL;


public class PathUtils {


    public static String getResourcePath(String name) {
        URL urlPath = Thread.currentThread()
                .getContextClassLoader()
                .getResource(name);
        if(urlPath!=null){
            return urlPath.getPath();
        }
        return null;
    }


    public static URL getResourcePathURL(String path) {
        return Thread.currentThread()
                .getContextClassLoader()
                .getResource(path);
    }

    public static String validateHandlerCreation(Enum<?> method, String path, Handler handler) {
        if (method == null || path.isEmpty() || handler == null) {
            return null;
        }
        if (!path.startsWith("/")) {
            return null;
        }
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }


    public static String extractPathFromRequest(HttpServletRequest request) {
        String path = "";
        String contextPath = request.getContextPath();
        if (!(contextPath.equals("/"))) {
            path = request.getRequestURI().substring(contextPath.length());
        }
        if (path.length() > 1 && path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;

    }
}