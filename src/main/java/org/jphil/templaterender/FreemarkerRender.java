package org.jphil.templaterender;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static org.jphil.core.JPhilConfig.getTemplatesPath;
import static org.jphil.utils.PathUtils.getResourcePath;


public class FreemarkerRender {


    public static void renderTemplate(String fileName, Object model, PrintWriter output) {
        render(fileName, model, output);

    }

    public static void renderTemplate(String fileName, List<?> models, PrintWriter output) {
        Map<String, Object> params = new HashMap<>();
        params.put("content", List.of(models));
        render(fileName, params, output);
    }

    public static void renderTemplate(String fileName, Map<?, ?> models, PrintWriter output) {
        render(fileName, models, output);

    }


    public static void render(String fileName, Object model, PrintWriter output) {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            cfg.setDirectoryForTemplateLoading(new File(Objects.requireNonNull(getResourcePath(getTemplatesPath()))));
            Template template = cfg.getTemplate(fileName + ".ftl");
            template.process(model, output);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

}

