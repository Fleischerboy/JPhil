package org.jphil.http;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContextThreadLocal {


    private static final ThreadLocal<Request> threadLocalRequest = new ThreadLocal<>();
    private static final ThreadLocal<Response> threadLocalResponse = new ThreadLocal<>();


    public static void create(HttpServletRequest req) {
        threadLocalRequest.set(new Request(req));
    }

    public static void create(HttpServletResponse res) {
        threadLocalResponse.set(new Response(res));
    }


    public static Request getRequestThreadLocal() {
        return threadLocalRequest.get();
    }

    public static Response getResponseThreadLocal() {
        return threadLocalResponse.get();
    }

    public static void removeRequestOnLocalThread(){
        threadLocalRequest.remove();

    }


    public static void removeResponseOnLocalThread(){
        threadLocalResponse.remove();

    }

}
