package tilak.sample.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/")
class IndexController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

        return "redirect:login/view";


    }






}
