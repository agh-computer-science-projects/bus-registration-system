package pl.edu.agh.to.busregistration.admin.passages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        passage.setBus(new Bus());
        model.addAttribute("passage", passage);

        List<Bus> buses = busService.findAllUnassigned();
        model.addAttribute("buses", buses);

        return "admin/passages/passage-form";
    }

    @PostMapping("/add")
    public String addPassage(@ModelAttribute("passage") Passage passage) {
        System.out.println(passage.getBus());
        System.out.println(passage.getName());
        if (passage.getBus() == null) {
            System.out.println("bus is null");
            return "redirect:/passages/add?error=true";
        }
        Bus bus = busService.findByRegistrationNumber(passage.getBus().getRegistrationNumber());
        bus.setAssigned(true);
        passage.setBus(bus);
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
