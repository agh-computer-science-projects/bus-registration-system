package pl.edu.agh.to.busregistration.admin.buses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {

    List<Bus> findAllByAssignedIsFalse();

    Bus findByRegistrationNumber(String registrationNumber);
}
