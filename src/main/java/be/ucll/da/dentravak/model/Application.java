package be.ucll.da.dentravak.model;

import be.ucll.da.dentravak.model.db.OrderRepository;
import be.ucll.da.dentravak.model.db.SandwichRepository;
import be.ucll.da.dentravak.model.domain.Order;
import be.ucll.da.dentravak.model.domain.Sandwich;
import be.ucll.da.dentravak.model.domain.BreadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    @Autowired SandwichRepository sandwichRepository;
    @Autowired OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            sandwichRepository.save(new Sandwich("Broodje gezond", BigDecimal.valueOf(3.5), "Ei, tomaat, sla, komkommer, mayonnaise"));
            sandwichRepository.save(new Sandwich("Smos",  BigDecimal.valueOf(2.8), "Kaas, hesp, ei, sla, mayonnaise"));
            sandwichRepository.save(Sandwich.SandwichBuilder.aSandwich().withName("Broodje boulette").withIngredients("Boulette, ei, sla, mayonnaise").withPrice(BigDecimal.valueOf(3.45)).build());

            //orderRepository.save(new Order(BreadType.BOTERHAMMEKES, "Smos", BigDecimal.valueOf(2.8)));
            //orderRepository.save(Order.OrderBuilder.aOrder().withBreadType(BreadType.TURKISH_BREAD).withName("Broodje gezond").withPrice(BigDecimal.valueOf(3.5)).build());
        };
    }
}