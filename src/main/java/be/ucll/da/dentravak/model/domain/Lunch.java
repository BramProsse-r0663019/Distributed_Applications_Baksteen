package be.ucll.da.dentravak.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Lunch {

    private String name;
    private List<String> ingredients = new ArrayList<>();
    private double prijs;

    public Lunch(String name, double prijs) {
        this.name = name;
        this.prijs = prijs;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
}
