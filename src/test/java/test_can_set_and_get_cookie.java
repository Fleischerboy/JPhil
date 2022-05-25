
import org.jphil.core.JPhil;
import org.jphil.handler.Handler;
import org.jphil.http.Request;
import org.jphil.http.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextPresent;

public class test_can_set_and_get_cookie {


    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/setCookie", (request, response) -> {
            response.cookie("role", "admin");
        });

        testApp.get("/getCookie", (request, response) -> {
            response.text(request.cookie("role"));
        });
        setBaseUrl("http://localhost:7777");
    }

    @Test
    public void setCookie(){
        beginAt("/setCookie");
        gotoPage("/getCookie");
        assertTextPresent("admin");
    }





}
