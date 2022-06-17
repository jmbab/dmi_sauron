package com.dmi_sauron.service;

import com.dmi_sauron.models.NinjoServerModel;
import com.dmi_sauron.repositories.NinjoServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class NinjoService {
    private final NinjoServiceRepository ninjoServiceRepository;
    public List<NinjoServerModel> findAll() { return ninjoServiceRepository.findAll(); }


}
