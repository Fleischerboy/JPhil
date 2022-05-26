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
### Request methods
````Java
protocol() // returnerer protokollen fra request eks: http/https
HttpMethod() //returnerer http-metode fra request
pathUrl() //  returnerer query url sti fra request
baseUrl() //  returnerer query uri uten queryString
contentType() // contentType fra request
contextPath() // context sti
QueryString() // returnerer query string
contentLength() //  returnerer innhold lengde
ip() // returnerer ip
host() // returnerer host
body() // returnerer body som en String
bodyAsBytes() // returnerer body som byte array
bodyAsInputStream() // returnerer Inputstream fra request
header(String name) // returnerer request header fra git header navn
headers() // returnerer alle headere i et map (header name, value)
formParam(String name) // form parameter fra navn
formParams() // map av alle form parametere
pathParam(String name) // path-parameter fra navn
pathParams() // map av alle path parametere
cookie(String name) // cookie verdi av git cookie navn
cookies() // map av alle cookies
session() // returnerer denne sessionen assosiert med denne requesten; hvis ingen session, vil den lage en.
requestSessionId()
changeSessionId() // Denne metoden endrer session id til gjeldende forespørsel/request og returnerer den nye session.
isRequestedSessionIdValid() // returnerer true eller false om session er gyldig.
isRequestedSessionIdFromCookie() // returnerer true eller false om session er fra cookie.
isRequestedSessionIdFromURL() // returnerer true eller false om session er fra URL.

````

### Response methods

````Java
text(String text) // Send text i respons
html(String htmlString) // Send html i respons
file(String fileName) // Send statisk fil i respons
json(Object json) // vil rendre object til Json
statusCode(int code) // set HTTP status kode
setContentType(String contentType) // set content type feks text/html, application/json;charset=UTF-8 etc.
redirect(String pathTo) // redirect klienten til den gitte pathen
setHeader(String headerName, String value) // set header i respons med å gi header navn og en verdi.
renderTemplate(String fileName, Map models) // render templatefil (.ftl) med FreeMarker template engine, fra git filnavn og modeler/objekter i et map.
renderTemplate(String fileName, Object model) // rendrer templatefil (.ftl) med FreeMarker template engine, fra git filnavn og model.
renderTemplate(String fileName, String key, List models) // render templatefil (.ftl) med FreeMarker template engine, fra git filnavn, nøkkel referanse til lista(som et hashmap (key,value), og modeler/objekter i en liste)
cookie(String name, String value) // set en respons cookie med navn og verdi.
cookie("name", "value", maxAge)   // set en respons cookie med navn, verdi og max-age.
````

### Access manager
JPhil has a functional interface AccessManager, which let’s you set per-endpoint authentication and/or authorization. It’s also common to use before-handlers for this, but enforcing per-endpoint roles give you much more explicit and readable code. You can implement your access-manager however you want. Below is an example implementation:

````Java
app.accessManager((handler, request, response, routeRoles) -> {
          Role userRole = getUserRole(request);
          if (routeRoles.contains(userRole)) {
              handler.handle(request, response);
          } else {
              response.statusCode(401);
          }
      });

  public static Role getUserRole(Request request) {
    // bestemme brukerrolle basert på request.
     // gjøres vanligvis ved å inspisere headere, cookies eller informasjonskapsler
  }

  enum Role implements RouteRole {
    ANYONE, ROLE_ONE, ROLE_TWO, ROLE_THREE;
}
app.get("/un-secured", (request, response) -> response.text("Hello"), Role.ANYONE);
app.get("/secured", (request, response) -> response.text("Hello"), Role.ROLE_ONE);

````

