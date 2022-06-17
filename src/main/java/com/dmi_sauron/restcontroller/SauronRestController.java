package com.dmi_sauron.restcontroller;

import com.dmi_sauron.models.NinjoServerModel;
import com.dmi_sauron.service.NinjoService;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class SauronRestController {

/*    // henvisning til singleton klasse for at hente command og dest variabler
    // Problem: kan ikke få fat i Resources fra en anden package mappe
    Resources resources = Resources.getInstance();
    String serverList = resources.getServerList();*/

    private NinjoService ninjoService;

    // HTTP Get List
    @GetMapping("/servicestatus")
    public List<NinjoServerModel> findAll()
    {
        return ninjoService.findAll();
    }

    @PostMapping(produces = "application/text", value = "server/{servername}")
    public ResponseEntity<String> getCommand(@PathVariable String serverName,
                                             @Valid @RequestBody JSONObject json) throws Exception
    {
        // Save serverName, timestamp and json fil
        // (as formatted String or ArrayList of key/value objects) in model class
        // model.addServerInfo(serverName,json);
        return ResponseEntity.ok().body(serverName);
    }

    @GetMapping(produces = "application/html", value = "status")
    public ResponseEntity<String> getCommand() throws Exception
    {
        // return ResponseEntity.ok().body(model.getHtmlReponse());
        // the model.getHtmlResponse could separately take care of the following:
        // Write a line for each server
        String html = "";
        // for (ServerInfo si : model.getServerInformations)
        // {
        // html += si.getName();
        // }
        return ResponseEntity.ok().body("Output");
    }
}

