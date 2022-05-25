import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class test_can_get_headers {

    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/getHeaders", (request, response) -> {
            Map<String, String> headers = request.headers();
            response.json(headers);
        });
        testApp.get("/getHeader", (request, response) -> {
            String header = request.header("Host");
            response.json(header);
        });
        setBaseUrl("http://localhost:7777");
    }

    @Test
    public void test_get_headers() {
        beginAt("/getHeaders");
        assertTextPresent("{\"Accept\":\"text/html,application/xhtml+xml,application/xml;q\\u003d0.9,*/*;q\\u003d0.8\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; rv:38.0) Gecko/20100101 Firefox/38.0\",\"Connection\":\"keep-alive\",\"Host\":\"localhost:7777\",\"Accept-Language\":\"en-US\",\"Accept-Encoding\":\"gzip, deflate\"}");
    }


    @Test
    public void test_get_specific_header() {
        beginAt("/getHeader");
        assertTextPresent("localhost:7777");
    }


}
