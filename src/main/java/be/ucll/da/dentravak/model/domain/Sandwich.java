package be.ucll.da.dentravak.model.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Sandwich")
public class Sandwich {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String name;
    private String ingredients;
    private double price;

    public Sandwich(){

    }

    public Sandwich(String name, double price, String ingredients ) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

//    public static class LunchBuilder{
//
//        private LunchBuilder(){}
//
//
//
//    }

}
