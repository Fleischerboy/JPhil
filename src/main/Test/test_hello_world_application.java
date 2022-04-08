import net.sourceforge.jwebunit.junit.WebTester;
import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;


public class test_hello_world_application {
    private static WebTester tester;

    @BeforeClass
    public static void prepare() {
        tester = new WebTester();
        JPhil app = JPhil.startWebServer(7070);
        app.get("/testhtml", (request, response) -> {
           response.html("<h1>Hello world</h1>");

        });
        tester.setBaseUrl("http://localhost:7070");

    }

    @Test
    public void test1() {
        tester.beginAt("/testhtml");
        tester.assertTextPresent("Hello World");
    }





}
