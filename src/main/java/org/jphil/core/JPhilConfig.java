package org.jphil.core;

public class JPhilConfig {


    public static String staticFilePath = "static";

    public static String templatesPath = "templates";

    public static void setStaticFilePath(String path) {
        JPhilConfig.staticFilePath = path;

    }


    public static void setTemplatePath(String path) {
        JPhilConfig.templatesPath = path;
    }



    public static String getStaticFilePath() {
        return staticFilePath;
    }

    public static String getTemplatesPath() {
        return templatesPath;
    }
}
