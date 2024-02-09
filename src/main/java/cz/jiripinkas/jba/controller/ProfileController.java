package cz.jiripinkas.jba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String about(Model model) {
        model.addAttribute("current", "profile");
        return "profile";
   }

}