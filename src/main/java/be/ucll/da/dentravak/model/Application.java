package be.ucll.da.dentravak.model;

import be.ucll.da.dentravak.model.db.LunchRepository;
import be.ucll.da.dentravak.model.domain.Sandwich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired SandWichRepository sandWichRepository) {
        return args -> {

            sandWichRepository.save(new Sandwich("Broodje gezond", BigDecimal.valueOf(3.5), "Brood, kaas, hesp, ei, sla"));
            sandWichRepository.save(new Sandwich("Smos",  BigDecimal.valueOf(2.8), "Brood, boulet, ei, sla, mayonnaise"));
        };
    }
}
