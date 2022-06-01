Github repositorie: https://github.com/Fleischerboy/JPhil

# JPhil
et enkelt web server rammeverk for webutvikling i java.

# Kom i gang med JPhil

````Java
import org.jphil.core.JPhil;
public class HelloWorld {
    public static void main(String[] args) {
        JPhil app = JPhil.startWebServer();
        app.get("/", (request, response) -> response.text("Hello World"));
    }
}
````


### Ant-style
Documentation: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/AntPathMatcher.html
- Wildcard
````
/hello/* matcher med 0 eller flere tegn
/hello/** matcher med 0 eller flere 'directories'
/he?llo matcher med et tegn
````

### Handlers
JPhil har tre typer behandlere: before-handlers, endpoint-handlers, og after-handlers. before, endpoint og after-handlers trenger tre deler:
- et verb, en av: before, get, post, put, delete and after
- en path, feks /, /helloWorld, /hello{name}
- en handler implementasjon, (request, response) -> {...}


### Before Handlers
Before-handlere matches før hver request
````Java
// Before-handlers kan refereres til som filtre, interceptorer eller middleware i andre biblioteker.
app.before((request, response) -> {
   // kjøres før alle requests
});

app.before("/path/*", (request, response) -> {
     // kjøres før request til /path/*
});
````

### Endpoint handlers
Ditt API er definert av endepunkt-behandlere, som er hovedtypen. med en GET-handler kan du sende server data til klienten, eller i POST-handler kan du motta data.
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
After-handlers kjøres etter hver request
````Java
//after-handlers kan refereres til som filtre, interceptorer eller middleware i andre biblioteker.
app.after((request, response) -> {
    // kjøres etter alle requests
});

app.after("/path/*", (request, response) -> {
    // kjøres etter request til /path/*
});

````


- Handler paths kan ha path-parameters. disse er tilgjengelig via request.pathParam("name");
````Java
app.get("/hello/{name}", (request, response) -> {
   response.text("Hello: " + request.pathParam("name"));
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
I JPhil er det et "functional interface" kalt AccessManager, som lar deg angi autentisering og autorisasjon per endepunkt. Før-handlere kan også brukes til dette, men å sette roller per endepunkt gjør koden mye mer eksplisitt og lesbar. Access-managers kan implementeres slik du vil. Nedenfor er et eksempel på implementering:

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

### Configuration
Veldig lite configurerings muligheter i dette stadie av JPhil rammeverket, men du har muligheten til å endre hvilken mappe JPhil skal lete etter filer inni resources mappen.
- Standarverdi for statiske filer er satt til: static inni resources mappen. Dessverre ikke mulig å endre fulle sti/path per idag.
- Standarverdi for dynamiske/template files er satt til:templates inni resources mappen. Dessverre ikke mulig å endre fulle sti/path per idag.
````Java
app.setStaticFilePath("staticFiles");
app.setTemplatePath("templateFiles");

````
