package com.dmi_sauron;

/*import com.dmi_sauron.domain.User;
import com.dmi_sauron.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DmiSauronApplication {

    public static void main(String[] args) {

        SpringApplication.run(DmiSauronApplication.class, args);
    }

/*    @Bean
    CommandLineRunner runner(UserService userService){
        return args -> {
            // read json and write to h2 database
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<User>> typeReference = new TypeReference<>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json_received/users.json");
            try {
                List<User> users = mapper.readValue(inputStream, typeReference);
                userService.save(users);
                System.out.println("Users Saved!");
            } catch (IOException e){
                System.out.println("Unable to save users: " + e.getMessage());
            }

        };
    }*/
}
