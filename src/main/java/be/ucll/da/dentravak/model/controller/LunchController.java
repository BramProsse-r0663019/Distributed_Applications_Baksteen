package be.ucll.da.dentravak.model.controller;


import be.ucll.da.dentravak.model.db.LunchRepository;
import be.ucll.da.dentravak.model.domain.Sandwich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LunchController {

    @Autowired
    LunchRepository lunchRepository;


    @RequestMapping("/")
    public List<Sandwich> index() {
        return (List<Sandwich>) lunchRepository.findAll();
    }
}