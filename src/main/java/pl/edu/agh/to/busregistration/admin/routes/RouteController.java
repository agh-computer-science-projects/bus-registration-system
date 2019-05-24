package pl.edu.agh.to.busregistration.admin.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to.busregistration.admin.buses.Bus;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public String getAllRoutes(Model model) {
        List<Route> routes = routeService.getAllRoutes();
        model.addAttribute("routes", routes);

        return "admin/routes/routes";
    }

    @GetMapping("/add")
    public String showRouteForm(Model model) {
        Route route = new Route();
        model.addAttribute("route", route);
        model.addAttribute("update", false);

        return "admin/routes/route-form";
    }

    @PostMapping("/add")
    public String addRoute(@ModelAttribute("route") Route route) {
        routeService.saveRoute(route);

        return "redirect:/routes";
    }

    @GetMapping("/update")
    public String updateRouteForm(@RequestParam("id") int routeId, Model model) {
        Optional<Route> route = routeService.findById(routeId);
        route.ifPresent(b -> model.addAttribute("route", route));
        model.addAttribute("update", true);

        return "admin/routes/route-form";
    }

    @GetMapping("/delete/{routeId}")
    public String deleteRoute(@PathVariable("routeId") int routeId) {
        routeService.deleteRoute(routeId);

        return "redirect:/routes";
    }
}
