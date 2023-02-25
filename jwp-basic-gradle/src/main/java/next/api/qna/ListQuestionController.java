package next.api.qna;

import core.web.ModelAndView;

import java.util.List;

import next.common.controller.AbstractController;
import next.api.qna.dao.QuestionDao;
import next.api.qna.model.Question;
import next.common.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ListQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(ListQuestionController.class);
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    protected ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) {
        List<Question> questions = null;
        try {
            questions = questionDao.findAll();
            request.setAttribute("questions", questions);
        } catch (SQLException e) {
            log.error(e.toString());
        }

        return new ModelAndView(new JspView("/qna/list.jsp")).addModel("questions", questions);
    }
}