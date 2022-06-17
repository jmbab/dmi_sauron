package com.dmi_sauron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SauronHomeController {

        // Returner index html siden når man rammer root endpoint
        // http://localhost:9090/ med GetMapping
        @GetMapping("/")
        public String index()
        {
            return "index";
        }

}
