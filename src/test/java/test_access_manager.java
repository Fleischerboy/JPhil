import model.User;
import net.sourceforge.jwebunit.exception.TestingEngineResponseException;
import org.jphil.core.JPhil;
import org.jphil.core.security.RouteRole;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.UserRepositoryTestData;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class test_access_manager {


    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        UserRepositoryTestData userDataRepositoryTestData = new UserRepositoryTestData();

        testApp.accessManager((handler, request, response, routeRoles) -> {
            String name = request.pathParam("name");
            Role userRole = getUserRole(userDataRepositoryTestData.getUserByFirstName(name));
            if (routeRoles.contains(userRole)) {
                handler.handle(request, response);
            } else {
                response.statusCode(401);
            }

        });
        testApp.get("/admin/{name}", (request, response) -> {
            String adminName = request.pathParam("name");
            response.html("<h1>Welcome to admin page " + adminName + "</h1>");

        }, Role.ADMIN);

        setBaseUrl("http://localhost:7777");
    }


    @Test
    public void test_access_manager_when_user_have_correct_role() {
        beginAt("/admin/philip");
        assertResponseCode(200);
        assertTextPresent("Welcome to admin page philip");
    }


    @Test
    public void test_access_manager_when_user_have_not_correct_role() {
        boolean thrown = false;
        int httpStatusCode = 0;
        try {
            beginAt("/admin/Fredrik");
        } catch (TestingEngineResponseException e) {
            httpStatusCode = e.getHttpStatusCode();
            thrown = true;
        }
        assertTrue(thrown);
        assertEquals(httpStatusCode, 401);
    }

    public static Role getUserRole(User user) {
        if (user != null) {
            String role = user.getRole();
            if (role == null) {
                return Role.ANYONE;
            } else if (role.equalsIgnoreCase("admin")) {
                return Role.ADMIN;
            } else if (role.equalsIgnoreCase("user")) {
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
