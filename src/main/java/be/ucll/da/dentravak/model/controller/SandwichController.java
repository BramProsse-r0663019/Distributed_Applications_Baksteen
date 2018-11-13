package be.ucll.da.dentravak.model.controller;

import be.ucll.da.dentravak.model.db.SandwichRepository;
import be.ucll.da.dentravak.model.domain.Sandwich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SandwichController {

    @Autowired
    SandwichRepository sandwichRepository;

    @RequestMapping("/sandwiches")
    public List<Sandwich> sandwiches() {
        return (List<Sandwich>) sandwichRepository.findAll();
    }

    @RequestMapping("/sandwiches", method = RequestMethod.POST)
    public void saveSandwiche(@RequestBody Sandwich sandwich) {
        sandwichRepository.save(sandwich);
    }

    @RequestMapping(value = "/sandwiches", method = RequestMethod.PUT)
    public void updateSandwich(@RequestBody Sandwich sandwich) {
        try {
            Sandwich toUpdateSandwich = (Sandwich) sandwichRepository.findById(sandwich.getId());
        } catch(NullPointerException e) {

        }
    }
}