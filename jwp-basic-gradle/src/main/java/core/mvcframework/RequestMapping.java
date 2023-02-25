package core.mvcframework;

import core.mvcframework.controller.Controller;
import core.mvcframework.controller.ForwardController;
import java.util.HashMap;
import java.util.Map;
import next.controller.HomeController;
import next.controller.qna.QuestionCreateController;
import next.controller.qna.QuestionFormController;
import next.controller.qna.ShowController;
import next.controller.qna.api.AddAnswerController;
import next.controller.qna.api.DeleteAnswerController;
import next.controller.user.SignUpController;
import next.controller.user.UserListController;
import next.controller.user.LoginController;
import next.controller.user.SignOutController;
import next.controller.user.ProfileController;
import next.controller.user.UpdateUserController;
import next.controller.user.UpdateUserFormController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
    private static final Logger log = LoggerFactory.getLogger(RequestMapping.class);
    private final Map<String, Controller> handlerMapping = new HashMap<>();

    public void initMapping() {
        // 로그인 필요없음
        handlerMapping.put("/", new HomeController());
        handlerMapping.put("/signUpForm", new ForwardController("user/form"));
        handlerMapping.put("/signUp", new SignUpController());
        handlerMapping.put("/loginForm", new ForwardController("user/login"));
        handlerMapping.put("/login", new LoginController());
        handlerMapping.put("/loginFailed", new ForwardController("user/login_failed"));

        // 로그인 필요
        handlerMapping.put("/users", new UserListController());
        handlerMapping.put("/users/signOut", new SignOutController());
        handlerMapping.put("/users/profile", new ProfileController());
        handlerMapping.put("/users/update", new UpdateUserController());
        handlerMapping.put("/users/updateForm", new UpdateUserFormController());
        handlerMapping.put("/qna/questionForm", new QuestionFormController());
        handlerMapping.put("/qna/createQuestion", new QuestionCreateController());
        handlerMapping.put("/qna/show", new ShowController());
        handlerMapping.put("/api/qna/addAnswer", new AddAnswerController());
        handlerMapping.put("/api/qna/deleteAnswer", new DeleteAnswerController());



    }

    public Controller getHandlerMapping(final String requestURI) {
        log.debug("requestURI={}", requestURI);
        return handlerMapping.get(requestURI);
    }
}


