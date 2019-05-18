package pl.edu.agh.to.busregistration.admin.passages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.agh.to.busregistration.admin.buses.Bus;
import pl.edu.agh.to.busregistration.admin.buses.BusService;

import java.util.List;

@Controller
@RequestMapping("/passages")
public class PassageController {

    private PassageService passageService;
    private BusService busService;

    @Autowired
    public PassageController(PassageService passageService, BusService busService) {
        this.passageService = passageService;
        this.busService = busService;
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

        List<Bus> buses = busService.findAllUnassigned();
        model.addAttribute("buses", buses);

        return "admin/passages/passage-form";
    }

    @PostMapping("/select-bus")
    public String addBusToPassage(@ModelAttribute("passage") Passage passage, Model model) {
        List<Bus> buses = busService.findAllUnassigned();

        model.addAttribute("passage", passage);
        model.addAttribute("buses", buses);

        return "admin/passages/passage-form-select-bus";
    }

    @PostMapping("/add-bus")
    public String addRouteToPassage(@ModelAttribute("passage") Passage passage,
                                    @ModelAttribute("bus") Bus bus,
                                    Model model) {
        passage.setBus(bus);

        model.addAttribute("passage", passage);
//        model.addAttribute("routes", routes);

        return "admin/passages/passage-form-select-route";
    }

    @PostMapping("/add")
    public String addPassage(@ModelAttribute("passage") Passage passage, Model model) {
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
