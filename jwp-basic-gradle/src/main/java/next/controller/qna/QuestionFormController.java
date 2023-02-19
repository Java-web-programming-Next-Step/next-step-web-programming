package next.controller.qna;

import core.mvcframework.controller.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import next.utils.SessionUtil;

public class QuestionFormController implements Controller {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (SessionUtil.isLogined(session, "user")) {
            return "qna/form";
        }
        return "redirect:/users/loginForm";
    }

}
