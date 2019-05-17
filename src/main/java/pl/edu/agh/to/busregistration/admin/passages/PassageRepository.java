package pl.edu.agh.to.busregistration.admin.passages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.busregistration.admin.buses.Bus;

@Repository
public interface PassageRepository extends JpaRepository<Passage, Integer> {
}
