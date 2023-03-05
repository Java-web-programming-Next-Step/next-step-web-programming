package next.api.web;

import core.annotation.Controller;
import core.annotation.RequestMapping;
import core.web.ModelAndView;
import next.common.view.JspView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping("/index")
    public ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView(new JspView("redirect:/question/list"));
    }
}