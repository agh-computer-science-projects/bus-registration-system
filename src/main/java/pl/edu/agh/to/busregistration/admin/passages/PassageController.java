package pl.edu.agh.to.busregistration.admin.passages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to.busregistration.admin.buses.Bus;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/passages")
public class PassageController {

    private PassageService passageService;

    @Autowired
    public PassageController(PassageService passageService) {
        this.passageService = passageService;
    }

    @GetMapping
    public String getAllPassages(Model model) {
        List<Passage> passages = passageService.getAllPassages();
        model.addAttribute("passages", passages);

        return "admin/passages/passages";
    }

    @GetMapping("/add")
    public String showAddPassageForm(Model model) {
        Passage passage = new Passage();
        model.addAttribute("passage", passage);

        return "admin/passages/passage-form";
    }

    @PostMapping("/add")
    public String addPassage(@ModelAttribute("passage") Passage passage) {
        passageService.savePassage(passage);

        return "redirect:/passages";
    }

//    @GetMapping("/update")
//    public String updateBusForm(@RequestParam("id") int busId, Model model) {
//        Optional<Bus> bus = busService.findById(busId);
//        bus.ifPresent(b -> model.addAttribute("bus", bus));
//        model.addAttribute("update", true);
//
//        return "admin/buses/bus-form";
//    }
//
//    @GetMapping("/delete/{busId}")
//    public String deleteBus(@PathVariable("busId") int busId) {
//        busService.deleteBus(busId);
//
//        return "redirect:/buses";
//    }
}
