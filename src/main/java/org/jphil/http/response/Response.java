package org.jphil.http.response;

import jakarta.servlet.http.HttpServletResponse;
import org.jphil.templaterender.FreemarkerRender;

import java.io.IOException;

public class Response {
    HttpServletResponse servletResponse;

    /**
     *
     * @param servletResponse
     */
    public Response(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }


    /**
     *
     * @param text
     */
    public void renderPainText(String text) {

    }


    /**
     *
     * @param htmlContent
     */
    public void renderHtmlContent(String htmlContent) {

    }

    public void renderTemplate() {
        FreemarkerRender.renderTemplate();
    }

    /**
     *
     * @param pathTo
     */
    public void redirect(String pathTo) {
        try {
            servletResponse.sendRedirect(pathTo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param code
     * @return
     */
    public Response statusCode(int code) {
        servletResponse.setStatus(code);
        return this;
    }

    /**
     * Set content type
     * @param contentType
     */
    public void setContentType(String contentType) {
        servletResponse.setContentType(contentType);
    }











}
