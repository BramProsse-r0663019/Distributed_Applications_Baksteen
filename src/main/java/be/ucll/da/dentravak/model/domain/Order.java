package be.ucll.da.dentravak.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID uuid;

    private BigDecimal sandwichPrice;
    private String sandwichName;

    public Order(BigDecimal sandwichPrice, String sandwichName) {
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
}
