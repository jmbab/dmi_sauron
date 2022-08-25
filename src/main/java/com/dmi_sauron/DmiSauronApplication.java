package com.dmi_sauron;

//import com.dmi_sauron.models.ServerSender;
//import com.dmi_sauron.models.ClientReceiver;
//import com.dmi_sauron.service.FindFileByExtension;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.dmi_sauron.models.NinjoServerModel;
import com.dmi_sauron.repositories.NinjoServiceRepository;
import com.dmi_sauron.service.NinjoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@SpringBootApplication
public class DmiSauronApplication {

    public static void main(String[] args) {

//        ServerSender serverSender = new ServerSender();
//        serverSender.main(null);
//        ClientReceiver clientReceiver = new ClientReceiver();
//        clientReceiver.main(null);
//        Path path = Paths.get("/resources/json_received");

        SpringApplication.run(DmiSauronApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(NinjoService ninjoService, NinjoServiceRepository ninjoServiceRepository) {
        return args -> {

            //  FindFileByExtension.findFilesByExtension();
            //  FindFileByExtension.findFiles("/json_received", "json");
            String jsonFileName = "127.0.0.1_serverinfo.json"; // i forEach loop over alle JSON filer i min json_received mappe

            // read JSON file and write to database
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<NinjoServerModel>> typeReference = new TypeReference<>() {};
            // Håndterer forward slash i URL path?
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json_received/"+jsonFileName);
            // how to read from file --> konvertere til String
            // bagefter pege på den rigtige fil i Repository

            try {
                // Serialization er en mekanisme til at konvertere et objekts tilstand (state) til en bytestrøm (byte stream).
                // Deserialization (herunder) er den omvendte proces, hvor bytestrømmen (byte stream) bruges til at genskabe
                // det faktiske Java-objekt i hukommelsen. Denne mekanisme bruges til at "persist" objektet.
                List<NinjoServerModel> ninjoServerModels = mapper.readValue(inputStream, typeReference);
                ninjoServiceRepository.saveAll(ninjoServerModels);
                ninjoService.findAll();
                System.out.println("Ninjo Servers saved!");
            } catch (IOException e){
                System.out.println("Unable to save Ninjo Servers: " + e.getMessage());
            }
        };
    }
}