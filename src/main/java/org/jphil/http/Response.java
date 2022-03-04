package org.jphil.http;
import jakarta.servlet.http.HttpServletResponse;
import org.jphil.templaterender.FreemarkerRender;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


public class Response {
    private PrintWriter writer;
    private HttpServletResponse servletResponse;

    /**
     * @param servletResponse
     * Response constructor
     */
    public Response(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    /**
     * Send plain text in response
     * @param text
     * Text to send in the response
     */
    public void text(String text) {
        setContentType(ContentType.TYPE_TEXT);
        // Todo: Write text to the response
    }

    /**
     * Send html in response
     * @param htmlString
     * The html to send
     */
    public void html(String htmlString) {
        setContentType(ContentType.TYPE_HTML);
    }

    /**
     * Method to send a file in the response
     * @param filePath
     * Path to the file
     */
    public void file(String filePath) {

    }

    public void json(Object json) {
        setContentType(ContentType.CONTENT_TYPE_JSON);
    }

    public void renderTemplate(String fileName, Map<String, Object> models) throws IOException {
        FreemarkerRender.renderTemplate(fileName, models, servletResponse.getWriter());
    }

    public void renderTemplate(String fileName, Object model) throws IOException {
        FreemarkerRender.renderTemplate(fileName, model, servletResponse.getWriter());
    }

    public void renderTemplate(String fileName, List<?> models) throws IOException {
        FreemarkerRender.renderTemplate(fileName, models, servletResponse.getWriter());
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


    public void setHeader(String headerName, String value) {

    }

    /**
     * Set content type
     * @param contentType
     */
    public void setContentType(String contentType) {

    }
}
