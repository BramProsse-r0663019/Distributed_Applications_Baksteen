package be.ucll.da.dentravak.model.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID uuid;

    private BigDecimal sandwichPrice;
    private String sandwichName;

    public Order(){}

    public Order(String sandwichName, BigDecimal sandwichPrice) {
        this.sandwichPrice = sandwichPrice;
        this.sandwichName = sandwichName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getSandwichPrice() {
        return sandwichPrice;
    }

    public void setSandwichPrice(BigDecimal sandwichPrice) {
        this.sandwichPrice = sandwichPrice;
    }

    public String getSandwichName() {
        return sandwichName;
    }

    public void setSandwichName(String sandwichName) {
        this.sandwichName = sandwichName;
    }

    public static class OrderBuilder{

        private String sandwichName;
        private BigDecimal sandwichPrice;
        private OrderBuilder(){}

        public static Order.OrderBuilder aOrder(){
            return new Order.OrderBuilder();
        }

        public Order.OrderBuilder withSandwichName(String sandwichName) {
            this.sandwichName = sandwichName;
            return this;
        }

        public Order.OrderBuilder withSandwichPrice(BigDecimal sandwichPrice) {
            this.sandwichPrice = sandwichPrice;
            return this;
        }

        public Order build(){
            Order order = new Order();
            order.sandwichName = sandwichName;
            order.sandwichPrice = sandwichPrice;
            return order;
        }
    }
}
