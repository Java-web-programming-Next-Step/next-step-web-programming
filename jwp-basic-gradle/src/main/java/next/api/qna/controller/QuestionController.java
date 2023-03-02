package next.api.qna.controller;

import core.annotation.Controller;
import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import core.web.ModelAndView;

import java.util.List;
import javax.servlet.http.HttpSession;

import next.api.qna.service.QuestionService;
import next.common.controller.AbstractController;
import next.api.qna.dao.AnswerDao;
import next.api.qna.dao.QuestionDao;
import next.api.qna.model.Answer;
import next.api.qna.model.Question;
import next.api.user.model.User;
import next.common.model.Result;
import next.common.view.JsonView;
import next.common.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
public class QuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionService questionService = QuestionService.getInstance();

    @Override
    @RequestMapping("/question")
    protected ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) {
        Long questionId = Long.parseLong(request.getParameter("questionId"));

        Question question = questionService.getQuestionByQuestionId(questionId);
        List<Answer> answers = questionService.getAnswersByQuestionId(questionId);

        return new ModelAndView(new JspView("/qna/show.jsp")).addModel("question", question).addModel("answers", answers);
    }

    @Override
    @RequestMapping(value = "/question", method = RequestMethod.POST)
    protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return new ModelAndView(new JspView("redirect:/user/login_failed.jsp"));
            }

            String title = request.getParameter("title");
            String contents = request.getParameter("contents");
            String questionIdParam = request.getParameter("questionId");

            questionService.putArticle(user, title, contents, questionIdParam);

            return new ModelAndView(new JspView("redirect:/question/list"));
    }

    @Override
    @RequestMapping(value = "/question", method = RequestMethod.DELETE)
    protected ModelAndView doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return new ModelAndView(new JsonView())
                        .addModel("result", Result.fail("질문 삭제를 위해선 로그인이 필요합니다."));
            }

            Long questionId = Long.parseLong(request.getParameter("questionId"));
            questionService.deleteQuestion(questionId, user);

            return new ModelAndView(new JsonView()).addModel("result", Result.ok());
        } catch (IllegalArgumentException e) {
            return new ModelAndView(new JsonView())
                    .addModel("result", Result.fail(e.getMessage()));
        }
    }
}
