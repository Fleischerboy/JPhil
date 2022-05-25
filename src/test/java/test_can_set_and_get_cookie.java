import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class test_can_set_and_get_cookie {


    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/setCookie", (request, response) -> {
            response.cookie("role", "admin");
            response.statusCode(200).html("Success!");
        });

        testApp.get("/getCookie", (request, response) -> {
            response.text(request.cookie("role"));
        });
        setBaseUrl("http://localhost:7777");
    }

    @Test
    public void setCookie() {
        beginAt("/setCookie");
        gotoPage("/getCookie");
        assertTextPresent("admin");
    }


}
