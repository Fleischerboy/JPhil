package org.jphil.http.response;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtil;
import org.jphil.core.JPhilConfig;
import org.jphil.http.ContentType;
import org.jphil.templaterender.FreemarkerRender;
import org.jphil.utils.PathUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
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
        servletResponse.setContentType(ContentType.CONTENT_TYPE_TEXT);
        getWriter().write(text);
    }


    /**
     *
     * @param htmlContent
     */
    public void renderHtmlContent(String htmlContent) {
        servletResponse.setContentType(ContentType.CONTENT_TYPE_HTML);
        PrintWriter writer = getWriter();
        writer.write(htmlContent);
    }

    /**
     *
     * @param fileName
     */
    public void sendStaticFile(String fileName) {
        if(!(fileName.startsWith("/"))) {
            fileName = "/" + fileName;
        }
        URL url = PathUtils.getResourcePathURL(JPhilConfig.getStaticFilePath() + fileName);
            if(url != null) {
                try {
                    IOUtil.copy(url.openStream(), getWriter());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }


    public void renderTemplate(String name, Map<String, Object> models) {
        FreemarkerRender.renderTemplate(name, models, getWriter());
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




    public PrintWriter getWriter() {
        if(writer == null) {
            try {
                return servletResponse.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return writer;
    }










}
