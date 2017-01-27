package tilak.sample.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tilak.sample.com.model.ProfileModel;
import tilak.sample.com.model.StatusModel;
import tilak.sample.com.service.ProfileService;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "/login")
class LoginController {

    @Autowired
    ProfileService profileService;

    private StatusModel statusModel;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(ProfileModel profileModel, Model model) {

        List<ProfileModel> profileModels=profileService.listProfile();


        model.addAttribute("profileTest",profileModels);


        return "login";


    }


    @RequestMapping(value = "/access", method = RequestMethod.POST)
    public String access(ProfileModel profileModel, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        List<ProfileModel> profileModels = profileService.loginCheck(profileModel);

        if (profileModels.isEmpty()) {
            statusModel = new StatusModel();
            statusModel.setStatus("UNSUCCESS");
            statusModel.setMessage("Incorrect Email Id and Password");
            statusModel.setMode("invalidMode");
            // model.addAttribute(statusModel);
            redirectAttributes.addFlashAttribute(statusModel);
            return "redirect:view";
        }

        session.setAttribute("sessionProfile", profileModels.get(0));

        return "redirect:../dashboard/view";


    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(Model model, HttpSession session) {

      /*

     ProfileModel profileModel=   (ProfileModel) session.getAttribute("sessionProfile");

        if (profileModel.getEmailId()!=null){

            session.invalidate();
        }*/

        session.invalidate();

        return "redirect:view";


    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signup(Model model) {

        return "register";


    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(ProfileModel profileModel, Model model, RedirectAttributes redirectAttributes) {

        statusModel= profileService.addProfile(profileModel);

        statusModel.setMessage("Successfully signed up");
        statusModel.setMode("registerMode");

        redirectAttributes.addFlashAttribute(statusModel);


        return "redirect:view";


    }





}
