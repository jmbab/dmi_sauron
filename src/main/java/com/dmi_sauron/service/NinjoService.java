package com.dmi_sauron.service;

import com.dmi_sauron.models.NinjoServerModel;
import com.dmi_sauron.repositories.NinjoServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

// man uddelegerer ansvar for kommunikation til databasen
// fra repository klassen til service klassen, som et mellemlag (separation of concerns)
@Service
@AllArgsConstructor
public class NinjoService {
    private final NinjoServiceRepository ninjoServiceRepository;

    public List<NinjoServerModel> findAll() { return ninjoServiceRepository.findAll(); }

    public NinjoServerModel findById(Long id) { return ninjoServiceRepository.findById(id).get(); }

    public List<NinjoServerModel> save(List<NinjoServerModel> ninjoServerModels) {
        return ninjoServiceRepository.saveAll(ninjoServerModels);
    }
}
