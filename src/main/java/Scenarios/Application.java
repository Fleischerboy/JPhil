package Scenarios;

import Scenarios.models.User;
import Scenarios.repository.UserDataRepository;
import jakarta.servlet.http.HttpSession;
import org.jphil.core.JPhil;
import org.jphil.core.security.RouteRole;
import org.jphil.http.Request;

import java.util.List;


public class Application {

    public static void main(String[] args) {
        /*
         * gjennom alle scenariene nedenfor vil det bli et resultat av enkel web applikasjon bygget oppå JPhil rammeverket.
         * UserDataRepository, model klasser som (User, TodoList og TodoItem), login.html, user-detail.ftl, users.ftl, er ikke en del av rammeverket, men brukes for eksempel data og demonstrering.
         * */

        UserDataRepository userRepository = new UserDataRepository();


        // Scenario 1: Starte webserveren på port 8080.
        //JPhil app = JPhil.startWebServer();


        // Scenario 1: (optional) start webserver on port 7070.
        JPhil app = JPhil.startWebServer(7070);


        // valgfritt scenario
        // Lage et endepunkt på webserveren med HTTP-metoden: GET, med URL-sti «/» og send ren text i respons.
        app.get("/", (request, response) -> response.text("Hello World"));


        //Scenario 2: Lage et endepunkt på webserveren med HTTP-metoden: GET, med URL-sti «/home» og send HTML kode i respons.
        app.get("/home", (request, response) -> {
            response.html("<h1>Hello home page</h1>");
        });


        // Scenario 3: Lage et endepunkt på instansen av JPhil med HTTP-metoden «get», med en URL-sti: «/login» og send en HTML fil kalt login.html til klientene.
        app.get("/login", (request, response) -> {
            response.file("login.html");
        });


        // Scenario 4:
        // Sette path hvor web-serveren skal servere statiske filer fra.
        // app.setStaticFilePath("staticFiles");


        //Scenario 5:
        // Lage et endepunkt på webserveren med HTTP-metoden: GET, med URL-sti «/api/users» og send java objekter av «User» klassen i et json-format til klientene i respons.
        app.get("/api/users", (request, response) -> {
            List<User> users = userRepository.getAllUsers();
            response.json(users);
        });


        // Scenario 6:
        // Sette ressurs sti for mal-filer til en mappe kalt «templateFiles» som ligger inni resources mappen.
        // app.setTemplatePath("templateFiles");


        // Scenario 7:
        // Lage et endepunkt på webserveren med HTTP-metoden «post» for kunne motta data fra klientene etter de har fylt inn login skjemaet som ligger i "login.html".
        app.post("/login", (request, response) -> {
            String username = request.formParam("uname");
            String password = request.formParam("psw");
            User user = userRepository.getSpecificUserByUsername(username);
            if (user != null && user.getPassword().equalsIgnoreCase(password)) {
                HttpSession session = request.session();
                session.setMaxInactiveInterval(1800);
                session.setAttribute("userId", user.getUserId());
                response.redirect("/profile/" + user.getUserId());
            } else {
                response.redirect("/");
            }
        });

        // log ut
        app.get("/logout", (request, response) -> {
            HttpSession session = request.session();
            session.invalidate();
            response.redirect("/login");
        });


        // Scenario 8:
        // Lage et parameterisert endepunkt med path «/profile/{userId}» på webserver med http metoden «get» og returner en dynamisk HTML-side basert på en template fil med navn «user-detail».
        app.get("/profile/{userId}", (request, response) -> {
            String userId = request.pathParam("userId");
            User user = userRepository.getSpecificUserById(Integer.parseInt(userId));
            String sessionAttribute = request.session().getAttribute("userId").toString();
            if (user != null && sessionAttribute.equals(userId)) {
                response.renderTemplate("user-detail", user);
            } else {
                response.statusCode(400);
            }
        });


        // Scenario 9: Begrense tilgang til endepunktet med HTTP-metoden GET og path «/admin» med autoriserings logikk.
        // admin API
        app.get("/admin", (req, res) -> {
            res.renderTemplate("users", "users", userRepository.getAllUsers());
        }, Role.ADMIN);

        app.accessManager((handler, request, response, routeRoles) -> {
            Role userRole = getUserRole(request, userRepository);
            if (routeRoles.contains(userRole)) {
                handler.handle(request, response);
            } else {
                response.statusCode(401);
            }
        });


        // Scenario 10: lage før-behandlere
        app.before((request, response) -> {
            System.out.println("before everything do something: ");
            // din kode her
        });


        // Scenario 11: lage etter-behandlere
        app.after((request, response) -> {
            System.out.println("after everything do something: ");
            // din kode her
        });


    }

    public static Role getUserRole(Request request, UserDataRepository userRepository) {
        String sessionAttribute = request.session().getAttribute("userId").toString();
        User user = userRepository.getSpecificUserById(Integer.parseInt(sessionAttribute));
        if (user != null) {
            String role = user.getRole();
            if (role == null) {
                return Role.ANYONE;
            } else if (role.equals("admin")) {
                return Role.ADMIN;
            } else if (role.equals("user")) {
                return Role.USER;
            } else {
                return Role.ANYONE;
            }
        }
        return Role.ANYONE;
    }

    enum Role implements RouteRole {
        ADMIN, USER, ANYONE
    }

}
