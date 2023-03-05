package next.api.qna.controller;

import core.annotation.Controller;
import core.annotation.RequestMapping;
import core.web.ModelAndView;
import next.api.qna.model.Question;
import next.api.qna.service.QuestionService;
import next.common.view.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class QuestionApiController {
    private static final Logger log = LoggerFactory.getLogger(QuestionApiController.class);
    private final QuestionService questionService = QuestionService.getInstance();

    @RequestMapping(value = "/api/qna/list")
    public ModelAndView questionList(HttpServletRequest request, HttpServletResponse response) {
        List<Question> questions = questionService.getQuestions();
        return new ModelAndView(new JsonView()).addModel("questions", questions);
    }
}