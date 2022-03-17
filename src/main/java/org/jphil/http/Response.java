package org.jphil.http;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtil;
import org.jphil.core.JPhilConfig;
import org.jphil.templaterender.FreemarkerRender;
import org.jphil.utils.PathUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class Response {
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
        try {
            servletResponse.getWriter().write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send html in response
     * @param htmlString
     * The html to send
     */
    public void html(String htmlString) {
        setContentType(ContentType.TYPE_HTML);
        try {
            servletResponse.getWriter().write(htmlString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to send a file in the response
     * @param fileName
     * Path to the file
     */
    public void file(String fileName) {
        if(!(fileName.startsWith("/"))) {
            fileName = "/" + fileName;
        }
        URL url = PathUtils.getResourcePathURL(JPhilConfig.getStaticFilePath() + fileName);
        if(url != null) {
            try {
                IOUtil.copy(url.openStream(), servletResponse.getWriter());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * will render an object as Json
     * @param json
     */
    public void json(Object json) {
        setContentType(ContentType.CONTENT_TYPE_JSON);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String jsonContent = gson.toJson(json);
        try {
            servletResponse.getWriter().write(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * redirect the client to the given path
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
     * set HTTP status
     * @param code HTTP status code
     * @return response
     */
    public Response statusCode(int code) {
       servletResponse.setStatus(code);
       return this;
    }


    public void setHeader(String headerName, String value) {

    }


    public void cookie(Cookie cookie) {
        servletResponse.addCookie(cookie);
    }


    /**
     * Set content type
     * @param contentType
     */
    public void setContentType(String contentType) {
        servletResponse.setContentType(contentType);
    }
}
