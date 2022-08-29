package com.dmi_sauron.restcontroller;

import com.dmi_sauron.models.NinjoServerModel;
import com.dmi_sauron.service.NinjoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ninjoservermodels")
public class SauronRestController {

    private final NinjoService ninjoService;

    // HTTP Get List
    @GetMapping("/list")
    public List<NinjoServerModel> findAll() { return ninjoService.findAll(); }

}

