package tilak.sample.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tilak.sample.com.model.CommentModel;
import tilak.sample.com.model.ProfileModel;
import tilak.sample.com.model.QuestionModel;
import tilak.sample.com.model.StatusModel;
import tilak.sample.com.service.CommentService;
import tilak.sample.com.service.ProfileService;
import tilak.sample.com.service.QuestionService;
import tilak.sample.com.utility.Utility;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "/comment")
class commentController {


    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @Autowired
    ProfileService profileService;

    StatusModel statusModel;

    @RequestMapping(value = "/question/{questionId}", method = RequestMethod.GET)
    public String searchByQuest(@PathVariable("questionId") String questionId, Model model) {

        QuestionModel questionModel=questionService.getById(new QuestionModel(questionId,null,null,null,null,null,null));

        // retrieve profile detail for the question
        ProfileModel profileModel= profileService.getById(new ProfileModel(questionModel.getProfileId(),null,null,null,null,null));

        questionModel.setProfile(profileModel);

        model.addAttribute("question",questionModel);

        //****


        List<CommentModel> commentModels=commentService.getByQuestion(new CommentModel(null,questionId,null,null,null));

        for(CommentModel commentModel:commentModels){
            // retreive profile detail for the comment provider
            profileModel= profileService.getById(new ProfileModel(commentModel.getProfileId(),null,null,null,null,null));
            commentModel.setProfile(profileModel);
            //**
        }

        model.addAttribute("comment",commentModels );

        // handle total view operation
        questionService.updateView(new QuestionModel(questionId,null,null,null,null,null,null));
        // **
        return "comment";


    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(CommentModel commentModel, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        ProfileModel profileModel= (ProfileModel) session.getAttribute("sessionProfile");

        commentModel.setProfileId(profileModel.getId());
        commentModel.setCreatedDate(new Utility().getDateAndTime());

        statusModel=new StatusModel();

        statusModel= commentService.addComment(commentModel);
        if (statusModel.getStatus().equals("SUCCESS")){

            statusModel.setMessage("Comment Posted Successfully");
            statusModel.setMode("commentUpdate");
            redirectAttributes.addFlashAttribute(statusModel);

        }

        // handle total view operation
        questionService.updatePost(new QuestionModel(commentModel.getQuestionId(),null,null,null,null,null,null));


        return "redirect:/comment/question/"+commentModel.getQuestionId();


    }



}
