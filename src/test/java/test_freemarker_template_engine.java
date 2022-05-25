import model.User;
import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class test_freemarker_template_engine {

    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/index", (request, response) -> {
            response.renderTemplate("index", new User(1, "Philip", "Fleischer", "phila@hotmail.com", "phila", "phila123", "admin"));
        });
        setBaseUrl("http://localhost:7777");
    }

    @Test
    public void freemarker() {
        beginAt("/index");
        assertTitleEquals("JPhil");
        assertResponseCode(200);
        assertTextPresent("Hello Philip");
    }
}
