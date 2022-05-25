import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;


public class test_can_fetch_data_from_post {


    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/input", (request, response) -> {
            response.file("form.html");
        });

        testApp.post("/input", (request, response) -> {
            String name = request.formParam("name");
            if (name != null) {
                response.redirect("/hello/" + name);
            }
        });


        testApp.get("/hello/{name}", (request, response) -> {
            response.text("hello " + request.pathParam("name"));

        });


        setBaseUrl("http://localhost:7777");
    }


    @Test
    public void can_fetch_form_data() {
        beginAt("/input");
        assertTitleEquals("JPhil");
        setTextField("name", "philip");
        submit();
        assertResponseCode(200);
        assertTextPresent("hello philip");


    }


}


