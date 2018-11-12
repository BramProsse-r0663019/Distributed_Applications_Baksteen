package be.ucll.da.dentravak.model.service;

import be.ucll.da.dentravak.model.db.LunchRepository;
import be.ucll.da.dentravak.model.domain.Sandwich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LunchService implements ILunchService {

    @Autowired
    private LunchRepository lunchRepository;


    public void addLunch(Sandwich sandwich) {
//        lunchRepository.add(sandwich);
    }


    @Override
    public List<Sandwich> findAll() {
        return (List<Sandwich>) lunchRepository.findAll();
    }
}
