package cz.jiripinkas.jba.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.service.UserService;

@Controller
public class ProfileController {

    @Autowired
	private UserService userService;

    @GetMapping("/profile")
    public String about(Principal principal, Model model) {
        String name = principal.getName();
        User currentUser = userService.findByName(name);
        model.addAttribute("current", "profile");
        model.addAttribute("user", currentUser);
        return "profile";
    }

    @PostMapping("/profile")
	public String update(@ModelAttribute User userModel, Principal principal, Model model) {
        String name = principal.getName();
        User currentUser = userService.findByName(name);

        String firstName = userModel.getFirstName();
        if (firstName != null) {
            currentUser.setFirstName(firstName);
        }
        String lastName = userModel.getLastName();
        if (lastName != null) {
            currentUser.setLastName(lastName);
        }
        String bio = userModel.getBio();
        if (bio != null) {
            currentUser.setBio(bio);
        }
        model.addAttribute("user", currentUser);
        userService.update(currentUser);
		return "profile";
	}

}
