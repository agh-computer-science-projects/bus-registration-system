package pl.edu.agh.to.busregistration.admin.schedules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SchedulesController {

    @GetMapping("/schedules")
    public String getSchedules() {
        return "admin/schedules/schedules";
    }
}