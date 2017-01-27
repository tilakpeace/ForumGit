package tilak.sample.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tilak.sample.com.model.ProfileModel;
import tilak.sample.com.model.QuestionModel;
import tilak.sample.com.model.StatusModel;
import tilak.sample.com.service.QuestionService;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "/dashboard")
class DashboardController {


    @Autowired
    QuestionService questionService;

    private StatusModel statusModel;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String dashboard(Model model, HttpSession session) {

        ProfileModel profileModel = (ProfileModel) session.getAttribute("sessionProfile");
        List<QuestionModel> questionModels = questionService.listQuestionByProfileId(profileModel);

        model.addAttribute("questions",questionModels);

        return "dashboard";

    }

}
