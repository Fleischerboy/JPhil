package org.jphil.http;

import jakarta.servlet.http.HttpServletResponse;

public class Response {
    HttpServletResponse servletResponse;

    public Response(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }
}
