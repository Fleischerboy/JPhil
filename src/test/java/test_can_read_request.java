import com.gargoylesoftware.htmlunit.WebResponse;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.junit.JWebUnit;
import org.jphil.core.JPhil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;

import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static org.junit.Assert.assertEquals;


public class test_can_read_request {


    private static String protocol;
    private static String baseUrl;
    private static String pathUrl;
    private static String servletPath;
    private static byte[] bodyAsBytes;
    private static String body;
    private static InputStream inputStream;
    private static String queryString;


    @BeforeClass
    public static void prepare() {
        JPhil testApp = JPhil.startWebServer(7777);
        testApp.get("/home", (request, response) -> {
            protocol = request.protocol();
            baseUrl = request.baseUrl();
            response.text("hello world");
        });

        testApp.get("/mycontext/**", (request, response) -> {
            servletPath = request.servletPath();
            inputStream = request.bodyAsInputStream();
            queryString = request.queryString();
            response.html("<h1>Hello world</h1>");

        });

        setBaseUrl("http://localhost:7777");
    }

    @Test
    public void test_read_protocol() {
        beginAt("/home");
        assertEquals(protocol, "http");


    }


    @Test
    public void test_read_baseUrl() {
        beginAt("/home");
        assertEquals(baseUrl, "/home");
    }

    @Test
    public void test_read_servlet_path() {
        beginAt("/mycontext/myservlet/hello/test?paramName=value");
        assertEquals(servletPath, "/mycontext/myservlet/hello/test");
    }


    @Test
    public void test_read_contentLength() {
        beginAt("/home");
        WebResponse response = ((HtmlUnitTestingEngineImpl) JWebUnit.getTestingEngine()).getWebResponse();
        assertEquals(response.getResponseHeaderValue("Content-Length"), Integer.toString(11));


    }


    @Test
    public void test_content_type() {
        beginAt("/mycontext/myservlet/");
        WebResponse response = ((HtmlUnitTestingEngineImpl) JWebUnit.getTestingEngine()).getWebResponse();
        assertEquals("Response type should be text/html, was: " + response.getContentType(), "text/html", response.getContentType());
    }


    @Test
    public void test_can_fetch_query_string() {
        beginAt("/mycontext/?name=philip&age=22");
        assertEquals(queryString, "name=philip&age=22");

    }


    @Test
    public void test() {


    }


}
