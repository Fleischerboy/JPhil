import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;


public class test_hello_world_application {


    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/testhtml", (request, response) -> {
            response.text("<h1>Hello World</h1>");
        });
        setBaseUrl("http://localhost:7777");
    }

    @Test
    public void testHelloWorld() {
        beginAt("/testhtml");
        assertResponseCode(200);
        assertTextPresent("Hello World");
    }


}
