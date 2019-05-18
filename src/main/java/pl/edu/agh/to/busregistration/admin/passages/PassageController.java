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
import pl.edu.agh.to.busregistration.admin.routes.Route;
import pl.edu.agh.to.busregistration.admin.routes.RouteService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/passages")
public class PassageController {

    private PassageService passageService;
    private BusService busService;
    private RouteService routeService;

    @Autowired
    public PassageController(PassageService passageService, BusService busService, RouteService routeService) {
        this.passageService = passageService;
        this.busService = busService;
        this.routeService = routeService;
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

        List<Route> unassignedRoutes = routeService.findAllUnassigned();

        model.addAttribute("routes", unassignedRoutes);
        model.addAttribute("bus", bus);

        return "admin/passages/passage-form-select-route";
    }

    @PostMapping("/add-passage")
    public String addPassage(@ModelAttribute("passage") Passage passage,
                             @ModelAttribute("route") Route route,
                             @ModelAttribute("bus") Bus bus) {

        bus.setPassage(passage);
        passage.setBus(bus);

        route.getPassages().add(passage);
        passage.setRoute(route);

        passage.setDate(new Date());

        busService.saveBus(passage.getBus());
        routeService.saveRoute(passage.getRoute());
        passageService.savePassage(passage);

        return "redirect:/routes";
    }

    @PostMapping("/add")
    public String addPassage(@ModelAttribute("passage") Passage passage, Model model) {
        return "redirect:/passages";
    }
}
