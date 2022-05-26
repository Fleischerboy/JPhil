# JPhil
A simple java web server framework for web development in java.

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
````Java
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


### Before Handlers
````Java
// You might know before-handlers as filters, interceptors, or middleware from other libraries.
app.before((request, response) -> {
    // runs before all requests
});

app.before("/path/*", (request, response) -> {
   // runs before request to /path/*
});
````

### Endpoint handlers
Endpoint handlers are the main handler type, and defines your API. You can add a GET handler to server data to a client, or a POST handler to receive some data.
````Java
app.get("/output", (request, response) -> {
    // some code
    response.json(object);
});

app.post("/input", (request, response) -> {
    // some code
    response.statusCode(201);
});

````

### After handlers

````Java
// You might know after-handlers as filters, interceptors, or middleware from other libraries.
app.after((request, response) -> {
    // run after all requests
});

app.after("/path/*", (request, response) -> {
    // runs after request to /path/*
});

````



