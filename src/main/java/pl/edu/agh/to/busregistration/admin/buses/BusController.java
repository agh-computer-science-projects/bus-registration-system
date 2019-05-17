package pl.edu.agh.to.busregistration.admin.buses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/buses")
public class BusController {

    private BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public String getAllBuses(Model model) {
        List<Bus> buses = busService.getAllBuses();
        model.addAttribute("buses", buses);

        return "admin/buses/buses";
    }

    @GetMapping("/add")
    public String showAddBusForm(Model model) {
        Bus bus = new Bus();
        model.addAttribute("bus", bus);
        model.addAttribute("update", false);

        return "admin/buses/bus-form";
    }

    @PostMapping("/add")
    public String addBus(@ModelAttribute("bus") Bus bus) {
        busService.saveBus(bus);

        return "redirect:/buses";
    }

    @GetMapping("/update")
    public String updateBusForm(@RequestParam("id") int busId, Model model) {
        Optional<Bus> bus = busService.findById(busId);
        bus.ifPresent(b -> model.addAttribute("bus", bus));
        model.addAttribute("update", true);

        return "admin/buses/bus-form";
    }

    @GetMapping("/delete/{busId}")
    public String deleteBus(@PathVariable("busId") int busId) {
        busService.deleteBus(busId);

        return "redirect:/buses";
    }
}
