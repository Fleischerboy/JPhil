# JPhil
simple webserver Framwork for web development in java.

#Quick start#

````Java
import org.jphil.core.JPhil;
public class HelloWorld {
    public static void main(String[] args) {
        JPhil app = JPhil.startWebServer();
        app.get("/", (request, response) -> response.text("Hello World"));
    }
}
````

##Basic Concept

###Route

Ant-style
- Wildcard
````
/hello/*
/hello/**
/he?llo
````


