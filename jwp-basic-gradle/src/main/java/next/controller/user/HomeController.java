package next.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import next.dao.QuestionDao;

public class HomeController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, Exception {
            QuestionDao questionDao = new QuestionDao();
            request.setAttribute("questions", questionDao.findAll());
            return this.jspView("/WEB-INF/index.jsp");
    }
}