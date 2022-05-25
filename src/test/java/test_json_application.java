
import model.User;
import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextPresent;

public class test_json_application {

    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/api/userJson", (request, response) -> {
            response.json(new User(1,"Philip", "Fleischer", "phila@hotmail.com", "phila", "phila123", "admin"));
        });
        setBaseUrl("http://localhost:7777");
    }

    @Test
    public void json() {
        beginAt("/api/userJson");
        assertResponseCode(200);
        assertTextPresent("{\"userId\":1,\"firstName\":\"Philip\",\"lastName\":\"Fleischer\",\"email\":\"phila@hotmail.com\",\"userName\":\"phila\",\"password\":\"phila123\",\"role\":\"admin\"}");
    }
}
