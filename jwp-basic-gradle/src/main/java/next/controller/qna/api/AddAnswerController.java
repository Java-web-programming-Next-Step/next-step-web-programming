package next.controller.qna.api;

import core.mvcframework.ModelAndView;
import core.mvcframework.controller.AbstractController;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import next.model.Answer;
import next.model.Result;
import next.model.User;
import next.service.AnswerService;
import next.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddAnswerController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);
    private final AnswerService answerService = new AnswerService();

    @Override
    public ModelAndView execute(final HttpServletRequest request, final HttpServletResponse response) {
        if (!SessionUtil.isLogined(request.getSession(), "user")) {
            return jsonView().addObject("result", Result.fail("로그인을 해야 합니다."));
        }
        User user = SessionUtil.getLoginObject(request.getSession(), "user");
        Answer answer = new Answer(
                user.getUserId(),
                request.getParameter("contents"),
                new Date(),
                Long.parseLong(request.getParameter("questionId")));
        log.debug("answer={}", answer);

        Answer savedAnswer = answerService.insertAnswer(answer);

        return jsonView().addObject("answer", savedAnswer)
                .addObject("result", Result.ok());
    }

}
