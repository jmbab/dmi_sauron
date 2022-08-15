package com.dmi_sauron.restcontroller;

import com.dmi_sauron.models.NinjoServerModel;
import com.dmi_sauron.service.NinjoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ninjoservermodels")
//@RequestMapping("/servers")
public class SauronRestController {

    private final NinjoService ninjoService;

    // HTTP Get List
    @GetMapping("/list")
    public List<NinjoServerModel> findAll() { return ninjoService.findAll(); }






    // HTTP Post, fx. create
/*    @PostMapping(value = "/post", consumes = "application/json")
    public void create(@RequestBody NinjoServerModel ninjoServerModel) {
        ninjoService.create(ninjoServerModel);
    }*/


   /* @PostMapping(produces = "application/text", value = "server/{servername}")
    public ResponseEntity<String> getCommand(@PathVariable String servername,
                                             @Valid @RequestBody JSONObject json) throws Exception
    {
        // Save serverName, timestamp and json fil
        // (as formatted String or ArrayList of key/value objects) in model class
        // model.addServerInfo(serverName,json);
        return ResponseEntity.ok().body(servername);
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
    }*/
}

