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
//  private List<String> ingredients;

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

//    public List<String> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(List<String> ingredients) {
//        this.ingredients = ingredients;
//    }

    public static class LunchBuilder{

        private LunchBuilder(){}
    }
}
