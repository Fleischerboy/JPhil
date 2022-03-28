package org.jphil.http;

import jakarta.servlet.http.HttpServletRequest;

public class RequestFactory {

    // TheadLocal construct allows us to store data that will be accessible only by a specific thread.
    private static final ThreadLocal<Request> threadLocal = new ThreadLocal<>();


    public static void create(HttpServletRequest req) {
        threadLocal.set(new Request(req));
    }


    public static Request getThreadLocal() {
        return threadLocal.get();
    }


    // remove the data from the ThreadLocal
    public static void remove(){
        threadLocal.remove();
    }
}
