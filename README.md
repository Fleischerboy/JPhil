# JPhil
simple webserver Framwork for web development in java.

# Quick start

````Java
import org.jphil.core.JPhil;
public class HelloWorld {
    public static void main(String[] args) {
        JPhil app = JPhil.startWebServer();
        app.get("/", (request, response) -> response.text("Hello World"));
    }
}
````

## Basic Concept

### Route

Ant-style

- path-parameters These are available via request.pathParam("key");
`````Java
app.get("/hello/{name}", (request, response) -> {
   response.text("Hello: " + request.pathParam("name"));
});
````

- Wildcard
````
/hello/*
/hello/**
/he?llo
````

### Handlers
JPhil has three main handler types: before-handlers, endpoint-handlers, and after-handlers. 
- A verb, one of: before, get, post, put, delete and after
- A path, ex: /, /helloWorld, /hello{name}
- A handler implementation, ex (request, response) -> {...}

