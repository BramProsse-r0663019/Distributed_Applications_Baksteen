package be.ucll.da.dentravak.model.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Sandwich")
public class Sandwich {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String name;
    private String ingredients;
    private BigDecimal price;

    public Sandwich(){
    }

    public Sandwich(String name, BigDecimal price, String ingredients ) {
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

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    //Normally put in testclass
    public static class SandwichBuilder{

        private String name;
        private String ingredients;
        private BigDecimal price;

        private SandwichBuilder(){}

        public static SandwichBuilder aSandwich(){
            return new SandwichBuilder();
        }

        public SandwichBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SandwichBuilder withIngredients(String ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public SandwichBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Sandwich build(){
            Sandwich sandwich = new Sandwich();
            sandwich.name = name;
            sandwich.ingredients = ingredients;
            sandwich.price = price;
            return sandwich;
        }
    }
}