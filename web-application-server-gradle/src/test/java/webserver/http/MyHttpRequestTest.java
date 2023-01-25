package webserver.http;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class MyHttpRequestTest {
    private String testDirectory = "./src/test/resources/";

    @Test
    public void request_GET() throws Exception {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_GET.http"));
        MyHttpRequest myHttpRequest = new MyHttpRequest(in);

        assertEquals("GET", myHttpRequest.getMethod());
        assertEquals("/user/create", myHttpRequest.getRequestPath());
        assertEquals("keep-alive", myHttpRequest.getHeader("Connection"));
        assertEquals("myid", myHttpRequest.getParameter("userId"));
    }

    @Test
    public void request_POST() throws Exception {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_POST.http"));
        MyHttpRequest myHttpRequest = new MyHttpRequest(in);

        assertEquals("POST", myHttpRequest.getMethod());
        assertEquals("/user/create", myHttpRequest.getRequestPath());
        assertEquals("keep-alive", myHttpRequest.getHeader("Connection"));
        assertEquals("myii", myHttpRequest.getParameter("userId"));
    }

    @Test
    public void request_POST_paramsInUrl() throws Exception {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_POST_paramsInUrl.http"));
        MyHttpRequest myHttpRequest = new MyHttpRequest(in);

        assertEquals("POST", myHttpRequest.getMethod());
        assertEquals("/user/create", myHttpRequest.getRequestPath());
        assertEquals("keep-alive", myHttpRequest.getHeader("Connection"));
        assertEquals("myjj", myHttpRequest.getParameter("userId"));
    }
}