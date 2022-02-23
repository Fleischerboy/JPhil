package org.jphil.utils;

import java.net.URL;

public class PathUtils {


    public static String getResourcePath(String name){
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
}
