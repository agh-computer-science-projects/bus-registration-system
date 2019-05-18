package pl.edu.agh.to.busregistration.admin.buses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    private BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public void saveBus(Bus bus) {
        busRepository.save(bus);
    }

    public Optional<Bus> findById(int busId) {
        if (busId <= 0) throw new IllegalArgumentException("Bus id must be number greater than zero");
        return busRepository.findById(busId);
    }

    public void deleteBus(int busId) {
        busRepository.deleteById(busId);
    }

    public List<Bus> findAllUnassigned() {
        return busRepository.findAllByAssignedIsFalse();
    }

    public Bus findByRegistrationNumber(String registrationNumber) {
        return busRepository.findByRegistrationNumber(registrationNumber);
    }
}
