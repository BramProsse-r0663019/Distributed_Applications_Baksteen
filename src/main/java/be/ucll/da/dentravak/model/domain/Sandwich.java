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
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

//    @Lob
//    @Convert(converter = JpaJsonConverter.class)
//    private List<Sandwich>


    public static class SandwichBuilder{

        private String name;
        private String ingredients;
        private BigDecimal price;

        private SandwichBuilder(){}

        public static SandwichBuilder aSandwich(){
            return new SandwichBuilder();
        }

        public SandwichBuilder getName() {
            return this;
        }

        public void withName(String name) {
            this.name = name;
        }

        public SandwichBuilder getIngredients() {
            return this;
        }

        public void withIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public SandwichBuilder getPrice() {
            return this;
        }

        public void withPrice(BigDecimal price) {
            this.price = price;
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
