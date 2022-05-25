import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class test_can_fetch_path_parameters {


    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/hello/{name}", (request, response) -> {
            response.text(request.pathParam("name"));
        });
        setBaseUrl("http://localhost:7777");
    }


    @Test
    public void name() {
        beginAt("/hello/philip");
        assertResponseCode(200);
        assertTextPresent("philip");
    }

}
