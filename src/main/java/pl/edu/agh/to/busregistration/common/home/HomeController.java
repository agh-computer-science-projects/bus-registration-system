package pl.edu.agh.to.busregistration.common.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "user/home";
    }

    @GetMapping("/admin-home")
    public String adminHome() {
        return "admin/admin_home";
    }
}
