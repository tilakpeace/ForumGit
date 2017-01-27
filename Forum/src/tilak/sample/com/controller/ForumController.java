package tilak.sample.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tilak.sample.com.model.QuestionModel;
import tilak.sample.com.model.StatusModel;
import tilak.sample.com.service.QuestionService;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "/forum")
class ForumController {


    @Autowired
    QuestionService questionService;

    private StatusModel statusModel;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewForum(Model model, HttpSession session) {

        List<QuestionModel> questionModels = questionService.questionSortByView();

        model.addAttribute("questions", questionModels);


        return "forum";

    }


    @RequestMapping(value = "/search/{topic}", method = RequestMethod.GET)
    public String searchByTopic(@PathVariable("topic") String topic, Model model, HttpSession session) {

        List<QuestionModel> questionModels = questionService.searchByTopic(topic);

        model.addAttribute("questions", questionModels);


        return "forum";

    }

}
