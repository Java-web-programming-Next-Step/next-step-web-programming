package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.web.View;
import next.model.User;
import next.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateUserFormController extends AbstractController {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormController.class);

    @Override
    protected View doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            log.debug("Only logged-in users can access.");
            return new JspView("redirect:/user/login.jsp");
        }
        User user = (User)value;
        if (!user.isSameUser(request.getParameter("userId"))) {
            log.debug("자신의 계정에만 접근할 수 있습니다.");
            return new JspView("redirect:/user/list");
        }

        request.setAttribute("user", user);
        return new JspView("/user/update.jsp");
    }
}