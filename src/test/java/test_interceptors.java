import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;

public class test_interceptors {


    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/home", (request, response) -> {
            response.text("hello world");
            System.out.println("endpoint");
        });

        testApp.before((request, response) -> {
            System.out.println("before everything");
        });

        testApp.before("/home", (request, response) -> {
            System.out.println("before /home");
        });


        testApp.after("/home", (request, response) -> {
            System.out.println("after /home");
        });

        testApp.after((request, response) -> {
            System.out.println("after everything");
        });

        setBaseUrl("http://localhost:7777");
    }


    @Test
    public void test_interceptor() {
        beginAt("/home");

    }
}
