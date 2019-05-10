package pl.edu.agh.to.busregistration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.agh.to.busregistration.forms.LoginForm;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "/login";
    }
}
