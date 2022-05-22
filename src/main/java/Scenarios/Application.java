package Scenarios;

import Scenarios.models.User;
import org.jphil.core.JPhil;
import org.jphil.core.security.RouteRole;
import org.jphil.http.Request;

import java.util.Arrays;
import java.util.List;

import static org.jphil.http.Mapping.EndPointMappingFactory.printMap;


/**
 * @hidden Class made for demonstrating scenarios using JPhil framework. this code is not part of the framework.
 */
public class Application {




    public static void main(String[] args) {


        /*
         * through all the scenarios down below it will be a result of a web application built in top of my backend framework called JPhil.
         *
         * */

        // Scenario 1: start webserver (default port is 8080)
        JPhil app = JPhil.startWebServer(7070);

        // optional: start webserver on port 7777.
       // JPhil app2 = JPhil.startServer(7777);


        //Scenario 2: Create an endpoint with url-path "/" on the webserver and send "Hello world" in text form.
        // use the response object to call on the method text() and write "Hello world"
        app.get("/", (request, response) -> response.text("Hello World"));


        //Scenario 2: Create an endpoint on the webserver with the http method "GET" with URL-path: "/" and give your clients some html code.
        // use the response object to call on html() with the html content inside the parenthesis/parameter.
        app.get("/home", (request, response) -> {
            response.html("<h1>Welcome to this amazing web application</h1><br>" +
                    "<a href=\"http://localhost:7777/login\" class=\"button\">Go to login page</a>" +
                    "<style> body{background-color: #E7E8D1;}" +
                    ".button {background-color: #B85042; color:white; text-decoration: none; padding: 15px 32px; margin: 5px; text-align: center; cursor: pointer; font-size: 14;}" +
                    "</style>");
        });



        // Scenario 3: set resource path for static files to a directory named "StaticFiles"
        // step 1: create a directory named "StaticFiles" inside the folder called "resources" in your IDE/project.
        // step 2: use app object and call on setStaticFilePath() with "staticFiles" as the parameter.
        // full path will be: (.../src/main/resources/StaticFiles).
        app.setStaticFilePath("StaticFiles");




        //Scenario 4: Create an endpoint on the webserver with the http method GET with URL-path: "/login" and send a html file to your clients.
        // and use the response object to call on file() with a file name "login.html" inside the parenthesis.
        app.get("/login", (request, response) -> {
            response.file("login.html");
        });



        // optional:
        // if you want to move out your handlers into a controller to achieve a
        // Model-view-controller pattern for a better scalability and readability of your web app when it gets big!.

        // option 1:
        // app.endPoint("/login").with(LoginController.class);

        // option 2:
       // LoginController loginController = new LoginController();
      // app.get("/login", loginController::login2);





        // Scenario 5: Create an endpoint on the webserver with http method POST with URL-path: "/login" to receive data from the clients.
        // use the request object to call on getFormParam("uname") & getFormParam("psw") to fetch the username and password data values from the form.
        app.post("/login", (request, response) -> {
            String username = request.formParam("uname");
            String password = request.formParam("psw");
            if(loginValidate(username, password)) {
                User user = getSpecificUserByUsername(username);
                if (user != null) {
                    response.redirect("/profile/" + user.getUserId());
                }
            }
            else {
                response.redirect("/login");
            }

        });




        // Scenario 6: set resource path for template files to a directory named "templateFiles".
        // step 1: create a directory named "templateFiles" inside the folder called "resources" in your IDE/project.
        // step 2: use app object and call on setTemplatePath() with "templateFiles" as the parameter.
        // full path will be: (.../src/main/resources/templateFiles).
        app.setTemplatePath("templateFiles");




        //Scenario 7: Create an endpoint on the webserver with http method GET, but now with parameterized url-path: "/{userId}".
        // step 1: fetch the pathParameter/pathVariable with the getPathParam() with a key name "userId" inside the parenthesis/parameter.
        // step 2: if the user was found in the database we will use the response object to call on renderTemplate() method which will
        // take the file name of the template and the model as parameters.
        app.get("/profile/{userId}", (request, response) -> {
            String userId = request.pathParam("userId");
            User user = getSpecificUserById(userId);
            if(user != null) {
                // This will generate the html code with the correct data variables/attributes from the user model.
                response.renderTemplate("user-detail", user);
            }
            else {
                response.statusCode(400);
            }

        });





        // authorization
        // Scenario 8: put an admin role on an endpoint with the http method GET with a URL-path /admin.
        // use response object and call on renderTemplate() method which will take the file name of the template and the list of models as parameters.
        app.get("/admin", (request, response) -> {
           if (getUserRole(request) == Role.ADMIN) {
               // here we want to list out all our users on the platform.
               List<User> allUsers = getAllUsers();
               response.renderTemplate("users", allUsers);

           } else {
               response.statusCode(404);
           }

        // only users with an admin role will have access to this recourse.
        }, Role.ADMIN);








        //Scenario 9: create a before-handler on path "/{userId}/*" and implement logic for authentication for our users before the actual endpoint-handler is executed.
        app.before("/home", (request, response) -> {
            System.out.println("before");

        });









        //Scenario 10: coming soon
        app.after((request, response) -> {

        });



        app.accessManager((handler, request, response, routeRoles) -> {
            Role userRole = getUserRole(request);
            if(routeRoles.contains(userRole)) {
                  handler.handle(request, response);
            } else {
                response.statusCode(401);
            }
        });

        app.get("/a", (request, response) -> {
            response.cookie("role", "admin");
        });


        app.get("/abc", (request, response) -> {
            response.html("<h1> hello </h1>");
        }, Role.ADMIN, Role.USER);



        app.before((request, response) -> {

        });

        System.out.println("*****************");
        printMap();


        System.out.println("********aop***********");


        app.before("/book/{userId}", (request, response) -> {
            System.out.println("Hello before");
            String id = request.pathParam("userId");
            System.out.println(id);
        });



        app.get("/lol", (request, response) -> {
            System.out.println(request.body());
            response.text("hello");


        });



        app.before((request, response) -> {
            // kjøres før alle requests
        });

        app.before("/path/*", (request, response) -> {
            // kjøres før request til /path/*
        });

        app.after("/", (request, response) -> {
            // kjøres etter request til /path/*
        });

        app.after((request, response) -> {
            // kjøres etter alle requests
        });




}


    enum Role implements RouteRole {
            ADMIN, USER, ANYONE
    }
    public static Role getUserRole(Request request) {
        String role = request.cookie("role");
        if(role == null) {
            return Role.ANYONE;
        }
        else if(role.equalsIgnoreCase("admin")) {
            return Role.ADMIN;
        }
        else if (role.equalsIgnoreCase("user")){
            return Role.USER;
        }
        else {
            return Role.ANYONE;
        }
    }

    private static List<User> getAllUsers() {

        return null;
    }

    private static User getSpecificUserByUsername(String username) {
        return null;
    }

    private static boolean loginValidate(String username, String password) {

        return false;
    }


    private static User getSpecificUserById(String userId) {
        return null;
    }




}


