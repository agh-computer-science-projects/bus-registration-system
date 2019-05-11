package pl.edu.agh.to.busregistration.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return "login";
    }

    @PostMapping("/handle-login")
    public String handleLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String targetUrl = "home";
        if(role.contains("ADMIN")) {
            targetUrl = "admin_home";
        }
        return targetUrl;
    }

    @GetMapping("/admin-home")
    public String adminHome() {
        return "admin_home";
    }
}
