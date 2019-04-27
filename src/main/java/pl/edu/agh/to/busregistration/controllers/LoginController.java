package pl.edu.agh.to.busregistration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.agh.to.busregistration.forms.LoginForm;

@Controller
public class LoginController {

    @GetMapping(value = {"/", "/login"})
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {

        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if ("admin".equals(username) && "admin".equals(password)) {
            return "admin_home";
        } else if ("user".equals(username) && "password".equals(password)) {
            return "home";
        } else {
            model.addAttribute("invalidCredentials", true);
            return "index";
        }
    }
}
