package be.ucll.da.dentravak.model.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private UUID sandwichId;
    private String name;
    private BreadType breadType;
    private LocalDateTime creationDate;
    private BigDecimal price;
    private String mobilePhoneNumber;
    private boolean printed;

    public Order() {
        this.creationDate = LocalDateTime.now();
        this.printed = false;
    }

    public Order(UUID sandwichId, BreadType breadType, String name, BigDecimal price, String mobilePhoneNumber) {
        this.sandwichId = sandwichId;
        this.name = name;
        this.breadType = breadType;
        this.creationDate = LocalDateTime.now();
        this.price = price;
        this.printed = false;
    }

    public UUID getId() {
        return this.id;
    }

    public void setUuid(UUID id) {
        this.id = id;
    }

    public UUID getSandwichId() {
        return this.sandwichId;
    }

    public void setSandwichId(String sandwichId) {
        this.sandwichId = UUID.fromString(sandwichId);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreadType() {
        return this.breadType.toString();
    }

    public void setBreadType(String breadType) {
        this.breadType = BreadType.valueOf(breadType);
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMobilePhoneNumber() {
        return this.mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isPrinted() {
        return printed;
    }

    public void setPrinted(boolean printed) {
        this.printed = printed;
    }

    //Normally put in testclass
    public static class OrderBuilder{

        private UUID sandwichId;
        private BreadType breadType;
        private String name;
        private BigDecimal price;
        private String mobilePhoneNumber;
        private OrderBuilder(){}

        public static Order.OrderBuilder aOrder(){
            return new Order.OrderBuilder();
        }

        public Order.OrderBuilder withSandwichId(UUID sandwichId) {
            this.sandwichId = sandwichId;
            return this;
        }

        public Order.OrderBuilder withBreadType(BreadType breadType) {
            this.breadType = breadType;
            return this;
        }

        public Order.OrderBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Order.OrderBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Order.OrderBuilder withMobilePhoneNumber(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
            return this;
        }

        public Order build(){
            Order order = new Order();
            order.sandwichId = sandwichId;
            order.breadType = breadType;
            order.name = name;
            order.price = price;
            order.mobilePhoneNumber = mobilePhoneNumber;
            return order;
        }
    }
}