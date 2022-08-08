package com.dmi_sauron;

/*import com.dmi_sauron.domain.User;
import com.dmi_sauron.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;*/
import com.dmi_sauron.models.ClientReceiver;
import com.dmi_sauron.models.NinjoServerModel;
import com.dmi_sauron.models.ServerSender;
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

        SpringApplication.run(DmiSauronApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(NinjoService ninjoService){
        return args -> {
            // read JSON file and load json
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<NinjoServerModel>> typeReference = new TypeReference<>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json_received/127.0.0.1_serverinfo.json");
            try {
                List<NinjoServerModel> ninjoServerModel = mapper.readValue(inputStream, typeReference);
                ninjoService.saveAll(ninjoServerModel);
                System.out.println("Ninjo Servers saved!");
            } catch (IOException e){
                System.out.println("Unable to save Ninjo Servers: " + e.getMessage());
            }

        };
    }
}
