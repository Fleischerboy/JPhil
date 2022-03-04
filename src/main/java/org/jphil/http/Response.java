package org.jphil.http;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.templaterender.FreemarkerRender;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


public class Response {
    private PrintWriter writer;
    private HttpServletResponse servletResponse;

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


    public void renderTemplate(String Filename, Map<String, Object> models) {
        FreemarkerRender.renderTemplate(Filename, models, getWriter());
    }

    public void renderTemplate(String Filename, Object model) {
        FreemarkerRender.renderTemplate(Filename, model, getWriter());
    }

    public void renderTemplate(String Filename, List<?> models) {
        FreemarkerRender.renderTemplate(Filename, models, getWriter());
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
     */
    public void statusCode(int code) {

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
