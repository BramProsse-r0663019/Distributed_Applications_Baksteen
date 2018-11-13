package be.ucll.da.dentravak.model;

import be.ucll.da.dentravak.model.db.LunchRepository;
import be.ucll.da.dentravak.model.domain.Sandwich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired LunchRepository lunchRepository) {
        return args -> {

            lunchRepository.save(new Sandwich("Broodje gezond", 3.5, "Brood, kaas, hesp, ei, sla"));
            lunchRepository.save(new Sandwich("Smos", 2.8, "Brood, boulet, ei, sla, mayonnaise"));
        };
    }
}
