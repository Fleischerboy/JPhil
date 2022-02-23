package org.jphil.templaterender;
import freemarker.template.*;
import org.jphil.core.JPhilConfig;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

import static org.jphil.utils.PathUtils.getResourcePath;

public class FreemarkerRender {



    public static void renderTemplate(String name, Map<String, Object> models, PrintWriter output) {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            cfg.setDirectoryForTemplateLoading(new File(Objects.requireNonNull(getResourcePath(JPhilConfig.getTemplatesPath()))));
            Template template = cfg.getTemplate(name);
            template.process(models,output);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

}

