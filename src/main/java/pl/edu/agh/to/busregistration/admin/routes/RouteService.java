package pl.edu.agh.to.busregistration.admin.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.busregistration.admin.buses.Bus;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public void saveRoute(Route route) {
        routeRepository.save(route);
    }

    public Optional<Route> findById(int busId) {
        if (busId <= 0) throw new IllegalArgumentException("Route id must be number greater than zero");
        return routeRepository.findById(busId);
    }

    public List<Route> findAllUnassigned() {
        return routeRepository.findAllByAssignedIsFalse();
    }

    public void deleteRoute(int routeId) {
        routeRepository.deleteById(routeId);
    }
}
