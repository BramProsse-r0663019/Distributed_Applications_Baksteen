package be.ucll.da.dentravak.model.controller;

import be.ucll.da.dentravak.model.db.SandwichRepository;
import be.ucll.da.dentravak.model.domain.Sandwich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sandwiches", produces = "application/json")
public class SandwichController {

    private final SandwichRepository sandwichRepository;

    public SandwichController(@Autowired SandwichRepository sandwichRepository) {
        this.sandwichRepository = sandwichRepository;
    }

    //Default = /sandwiches & GET method
    @RequestMapping()
    public List<Sandwich> sandwiches() {
        return (List<Sandwich>) sandwichRepository.findAll();
    }

    //Url "/sandwiches/find?name=..."
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    //Sandwich meegeven in body en niet in path (@PathVariable)
    public Sandwich sandwich(@RequestBody String name) {
        List<Sandwich> sandwiches = sandwiches();
        for (Sandwich sandwich : sandwiches) {
            if (sandwich.getName().toLowerCase().equals(name.toLowerCase())) {
                return  sandwich;
            }
        }
        //Geen sandwich gevonden dus lege returnen
        return new Sandwich();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveSandwich(@RequestBody Sandwich sandwich) {
        sandwichRepository.save(sandwich);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateSandwich(@RequestBody Sandwich sandwich) {
        //Optional want kan dat Sandwich niet aanwezig is
        Optional<Sandwich> maybeFoundSandwich = sandwichRepository.findById(sandwich.getId());
        if(maybeFoundSandwich.isPresent()) {
            Sandwich foundSandwich = maybeFoundSandwich.get();
            foundSandwich.setName(sandwich.getName());
            foundSandwich.setPrice(sandwich.getPrice());
            foundSandwich.setIngredients(sandwich.getIngredients());
        }
    }
}