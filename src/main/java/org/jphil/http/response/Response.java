package org.jphil.http.response;

import jakarta.servlet.http.HttpServletResponse;

public class Response {
    HttpServletResponse servletResponse;

    public Response(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }
}
