package org.jphil.http.response;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.templaterender.FreemarkerRender;
import java.io.PrintWriter;
import java.util.Map;


public class Response {
    private PrintWriter writer;
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

    /**
     *
     * @param fileName
     */
    public void sendStaticFile(String fileName) {

    }


    public void renderTemplate(String name, Map<String, Object> models) {
        FreemarkerRender.renderTemplate(name, models, getWriter());
    }

    /**
     *
     * @param pathTo
     */
    public void redirect(String pathTo) {

    }


    /**
     *
     * @param code
     * @return
     */
    public Response statusCode(int code) {

        return null;
    }

    /**
     * Set content type
     * @param contentType
     */
    public void setContentType(String contentType) {

    }




    public PrintWriter getWriter() {
        return null;
    }










}
