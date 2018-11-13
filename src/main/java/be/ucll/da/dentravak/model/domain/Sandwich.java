package be.ucll.da.dentravak.model.domain;

import org.springframework.data.jpa.repository.query.Jpa21Utils;

import javax.persistence.*;
import java.math.BigDecimal;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 260df519169edf2c5daba76c5fc4a77f9fa580e6
=======
>>>>>>> 260df519169edf2c5daba76c5fc4a77f9fa580e6
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
//  private List<String> ingredients;
>>>>>>> 260df519169edf2c5daba76c5fc4a77f9fa580e6
=======
//  private List<String> ingredients;
>>>>>>> 260df519169edf2c5daba76c5fc4a77f9fa580e6

    public Sandwich(){

    }

    public Sandwich(String name, BigDecimal price, String ingredients ) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

//  public Sandwich(String name, double price, List<String> ingredients) {
//      this.name = name;
//      this.price = price;
//  }

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

//    public static class LunchBuilder{
//
//        private LunchBuilder(){}
//
//
//
//    }

<<<<<<< HEAD
<<<<<<< HEAD
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

=======
=======
>>>>>>> 260df519169edf2c5daba76c5fc4a77f9fa580e6
    public static class LunchBuilder{

        private LunchBuilder(){}
    }
<<<<<<< HEAD
>>>>>>> 260df519169edf2c5daba76c5fc4a77f9fa580e6
=======
>>>>>>> 260df519169edf2c5daba76c5fc4a77f9fa580e6
}
