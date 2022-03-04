package Scenarios;

import org.jphil.annotation.GetMapping;
import org.jphil.handler.Handler;
import org.jphil.http.HttpMethod;
import org.jphil.http.Request;
import org.jphil.http.Response;

public class LoginController {

    public LoginController() {
    }

    @GetMapping(path = "/", httpMethod = HttpMethod.GET)
    public Handler login() {
        return (request, response) -> {
            response.file("login.html");
        };
    }


    public void login2(Request request, Response response) {
        response.file("login.html");
    }
    
}
