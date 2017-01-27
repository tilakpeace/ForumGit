package tilak.sample.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tilak.sample.com.model.ProfileModel;
import tilak.sample.com.model.QuestionModel;
import tilak.sample.com.model.StatusModel;
import tilak.sample.com.service.QuestionService;
import tilak.sample.com.utility.Utility;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/newQuestion")
class NewQuestionController {

    @Autowired
    QuestionService questionService;

    private StatusModel statusModel;


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewQuestion(Model model) {

        return "newQuestion";

    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addQuestion(QuestionModel questionModel, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        ProfileModel profileModel = (ProfileModel) session.getAttribute("sessionProfile");

        questionModel.setProfileId(profileModel.getId());
        questionModel.setView("0");
        questionModel.setPost("0");
        questionModel.setCreatedDate(new Utility().getDateAndTime());

        statusModel=new StatusModel();

        statusModel= questionService.addQuestion(questionModel);
        if (statusModel.getStatus().equals("SUCCESS")){

            statusModel.setMessage("Question Posted Successfully");
            statusModel.setMode("questionUpdate");
            redirectAttributes.addFlashAttribute(statusModel);

        }


        return "redirect:../dashboard/view";

    }

}
