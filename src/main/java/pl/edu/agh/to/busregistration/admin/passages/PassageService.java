package pl.edu.agh.to.busregistration.admin.passages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassageService {

    private PassageRepository passageRepository;

    @Autowired
    public PassageService(PassageRepository passageRepository) {
        this.passageRepository = passageRepository;
    }

    public List<Passage> getAllPassages() {
        return passageRepository.findAll();
    }

    public void savePassage(Passage passage) {
        passageRepository.save(passage);
    }
}
