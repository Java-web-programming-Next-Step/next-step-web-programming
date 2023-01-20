package customwebserver;

import controller.Controller;
import controller.LoginController;
import controller.UserController;
import customwebserver.http.HttpRequest;
import customwebserver.http.HttpResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.enums.HttpMethod;

public class FrontController {
    private static final Logger log = LoggerFactory.getLogger(FrontController.class);
    private static final Map<String, Object> handlerMapping = new HashMap<>();

    static {
        handlerMapping.put("/user/create", new UserController());
        handlerMapping.put("/user/login", new LoginController());
    }

    private final HttpRequest httpRequest;
    private final HttpResponse httpResponse;

    public FrontController(final HttpRequest httpRequest, final HttpResponse httpResponse) {
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
    }

    public void doProcess() throws IOException {
        requestDispatch();
    }

    /**
     * 1. 컨트롤러가 존재하는지 체크
     * 2. 정적 파일 체크
     */
    private void requestDispatch() throws IOException {
        Controller requestController = (Controller) handlerMapping.get(httpRequest.getRequestUri());
        if (requestController == null) {
            responseStaticUri();
            return;
        }
        if (httpRequest.containMethod(HttpMethod.GET)) {
            boolean success = requestController.doGet(httpRequest, httpResponse);
            responseUriWithEmptyData(success);
        }
        if (httpRequest.containMethod(HttpMethod.POST)) {
            boolean success = requestController.doPost(httpRequest, httpResponse);
            responseUriWithEmptyData(success);
        }
    }

    private void responseStaticUri() throws IOException {
        httpResponse.successStaticUri(httpRequest.getRequestUri());
    }

    private void responseUriWithEmptyData(final boolean success) {
        if (success) {
            httpResponse.successMappingUri();
        }
    }

}