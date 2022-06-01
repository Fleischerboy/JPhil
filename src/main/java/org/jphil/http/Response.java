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

import static org.eclipse.jetty.util.StringUtil.isEmpty;

/**
 * Class of Response
 * @author Philip
 */
public class Response {
    private final HttpServletResponse servletResponse;

    /**
     * @param servletResponse Response constructor
     */
    public Response(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    /**
     * Send plain text in response
     *
     * @param text Text to send in the response
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
     *
     * @param htmlString The html to send
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
     *
     * @param fileName Path to the file
     */
    public void file(String fileName) {
        if (!(fileName.startsWith("/"))) {
            fileName = "/" + fileName;
        }
        URL url = PathUtils.getResourcePathURL(JPhilConfig.getStaticFilePath() + fileName);
        if (url != null) {
            try {
                IOUtil.copy(url.openStream(), servletResponse.getWriter());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * will render an object as Json
     *
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


    /**
     * @param fileName to the specific template file
     * @param models   to the associated/related template file send as a map
     */
    public void renderTemplate(String fileName, Map<String, Object> models) {
        try {
            FreemarkerRender.renderTemplate(fileName, models, servletResponse.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param fileName fileName to the specific template file
     * @param model    model to the associated/related template file send as a single object
     */
    public void renderTemplate(String fileName, Object model) {
        try {
            FreemarkerRender.renderTemplate(fileName, model, servletResponse.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * @param fileName fileName to the specific template file
     * @param models   models to the associated/related template file send as a list.
     */
    public void renderTemplate(String fileName, String key, List<?> models) {
        try {
            FreemarkerRender.renderTemplate(fileName, key, models, servletResponse.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * redirect the client to the given path
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
     * set HTTP status
     *
     * @param code HTTP status code
     */
    public Response statusCode(int code) {
        servletResponse.setStatus(code);
        return this;
    }


    /**
     * Set content type
     *
     * @param contentType
     */
    public void setContentType(String contentType) {
        servletResponse.setContentType(contentType);
    }


    /**
     * @param headerName for the header
     * @param value      for the header
     */
    public void setHeader(String headerName, String value) {
        servletResponse.setHeader(headerName, value);
    }


    /**
     * Adds not persistent cookie to the response.
     *
     * @param name  name of the cookie
     * @param value of the cookie
     */
    public void cookie(String name, String value) {
        cookie("/", name, value, -1, false, false);
    }


    /**
     * Adds cookie to the response.
     *
     * @param name   of the cookie
     * @param value  of the cookie
     * @param maxAge max age of the cookie in seconds (negative for the not persistent cookie, zero - deletes the cookie)
     */
    public void cookie(String name, String value, int maxAge) {
        cookie("/", name, value, maxAge, false, false);
    }


    /**
     * Adds cookie to the response.
     *
     * @param name    of the cookie
     * @param value   of the cookie
     * @param maxAge  max age of the cookie in seconds (negative for the not persistent cookie, zero - deletes the cookie)
     * @param secured cookie will be secured
     */
    public void cookie(String name, String value, int maxAge, boolean secured) {
        cookie("/", name, value, maxAge, secured, false);
    }

    /**
     * Adds cookie to the response.
     *
     * @param name     of the cookie
     * @param value    of the cookie
     * @param maxAge   max age of the cookie in seconds (negative for the not persistent cookie, zero - deletes the cookie)
     * @param secured  if true : cookie will be secured
     * @param httpOnly if true: cookie will be marked as http only
     */
    public void cookie(String name, String value, int maxAge, boolean secured, boolean httpOnly) {
        cookie("/", name, value, maxAge, secured, httpOnly);
    }


    /**
     * Adds cookie to the response.
     *
     * @param path     of the cookie
     * @param name     of the cookie
     * @param value    of the cookie
     * @param maxAge   max age of the cookie in seconds (negative for the not persistent cookie, zero - deletes the cookie)
     * @param secured  if true : cookie will be secured
     * @param httpOnly if true: cookie will be marked as http only
     */
    public void cookie(String path, String name, String value, int maxAge, boolean secured, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        if (isEmpty(path)) {
            path = "/";
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(secured);
        cookie.setHttpOnly(httpOnly);
        servletResponse.addCookie(cookie);
    }


    /**
     * add cookie
     *
     * @param cookie
     */
    public void cookie(Cookie cookie) {
        servletResponse.addCookie(cookie);
    }


}
