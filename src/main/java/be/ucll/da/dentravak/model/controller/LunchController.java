package be.ucll.da.dentravak.model.controller;


import be.ucll.da.dentravak.model.db.LunchRepository;
import be.ucll.da.dentravak.model.domain.Sandwich;
import be.ucll.da.dentravak.model.service.ILunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LunchController {

    @Autowired
    ILunchService lunchService;

//    @Autowired
//    LunchRepository repository;

    @RequestMapping("/")
    public List<Sandwich> index() {
//        repository.save(new Sandwich("Broodje gezond", 3.5, "Brood, kaas, hesp, ei, sla"));
//        repository.save(new Sandwich("Smos", 2.8, "Brood, boulet, ei, sla, mayonnaise"));
        return (List<Sandwich>) lunchService.findAll();
    }
}
