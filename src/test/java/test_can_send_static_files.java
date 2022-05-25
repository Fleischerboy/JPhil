import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class test_can_send_static_files {

    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/home", (request, response) -> {
            response.file("home.html");
        });
        setBaseUrl("http://localhost:7777");
    }


    @Test
    public void staticFile() {
        beginAt("/home");
        assertTitleEquals("JPhil");
        assertResponseCode(200);
        assertTextPresent("Hello welcome to the home page!");
    }
}
