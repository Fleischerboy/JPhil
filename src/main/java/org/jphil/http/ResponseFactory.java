package org.jphil.http;

import jakarta.servlet.http.HttpServletResponse;

public class ResponseFactory {

    // TheadLocal construct allows us to store data that will be accessible only by a specific thread.
    private static final ThreadLocal<Response> threadLocal = new ThreadLocal<>();

    public static void create(HttpServletResponse res) {
        threadLocal.set(new Response(res));
    }

    public static Response getThreadLocal() {
        return threadLocal.get();
    }

    // remove the data from the ThreadLocal
    public static void remove(){
        threadLocal.remove();
    }


}
