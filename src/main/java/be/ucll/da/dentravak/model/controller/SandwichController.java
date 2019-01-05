package be.ucll.da.dentravak.model.controller;

import be.ucll.da.dentravak.model.db.SandwichRepository;
import be.ucll.da.dentravak.model.domain.Sandwich;
import be.ucll.da.dentravak.model.domain.SandwichPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import javax.inject.Inject;
import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.*;


//Class to sort sandwiches
class SortByPreferences implements Comparator<Sandwich>
{
    private SandwichPreferences preferences;

    SortByPreferences(SandwichPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public int compare(Sandwich s1, Sandwich s2) {

        //When both breads are rated
        if(preferences.getRatingForSandwich(s1.getId()) != null && preferences.getRatingForSandwich(s2.getId()) != null) {
            //Bread with biggest rating has to come first
            if(preferences.getRatingForSandwich(s1.getId()) > preferences.getRatingForSandwich(s2.getId())){
                return -1;
            }
            else if(preferences.getRatingForSandwich(s1.getId()) < preferences.getRatingForSandwich(s2.getId())){
                return 1;
            }
            //Equal
            return 0;
        } else if (preferences.getRatingForSandwich(s1.getId()) == null && preferences.getRatingForSandwich(s2.getId()) == null) {
            return 0;
        } else if (preferences.getRatingForSandwich(s1.getId()) == null) {
            return 1;
        } else {
            return -1;
        }
    }
}

@RestController
@RequestMapping(value = "/sandwiches", produces = "application/json")
public class SandwichController {

    @Inject
    private DiscoveryClient discoveryClient;

    @Inject
    private SandwichRepository sandwichRepository;

    @Inject
    private RestTemplate restTemplate;

    public SandwichController(@Autowired SandwichRepository sandwichRepository) {
        this.sandwichRepository = sandwichRepository;
    }

    //Default = /sandwiches & GET method
    @CrossOrigin
    @RequestMapping(value = "")
    public List<Sandwich> sandwiches() {
        try {
            //Hardcoded mobile phonenumber just for testing
            SandwichPreferences preferences = getPreferences("05");
            List<Sandwich> allSandwiches = (List<Sandwich>) sandwichRepository.findAll();
            SortByPreferences sbp = new SortByPreferences(preferences);
            Collections.sort(allSandwiches, sbp);
            return allSandwiches;
        } catch (ServiceUnavailableException e) {
            return (List<Sandwich>) sandwichRepository.findAll();
        } catch(Exception e){
            return (List<Sandwich>) sandwichRepository.findAll();
        }
    }

    @GetMapping("/getpreferences/{emailAddress}")
    public SandwichPreferences getPreferences(@PathVariable String emailAddress) throws RestClientException, ServiceUnavailableException {
        URI service = recommendationServiceUrl()
                .map(s -> s.resolve("/recommendation/recommend/" + emailAddress))
                .orElseThrow(ServiceUnavailableException::new);
        return restTemplate
                .getForEntity(service, SandwichPreferences.class)
                .getBody();
    }

    public Optional<URI> recommendationServiceUrl() {
        return discoveryClient.getInstances("recommendation")
                .stream()
                .map(si -> si.getUri())
                .findFirst();
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}")
    //Sandwich meegeven in body en niet in path (@PathVariable)
    public Sandwich sandwich(@PathVariable UUID id) {
        Optional<Sandwich> maybeFoundSandwich = sandwichRepository.findById(id);
        if (maybeFoundSandwich.isPresent()) {
            Sandwich foundSandwich = maybeFoundSandwich.get();
            return foundSandwich;
        }
        throw new SandwichNotFoundException();
    }

    @CrossOrigin
    @RequestMapping(value = "/name/{name}")
    //Sandwich meegeven in body en niet in path (@PathVariable)
    public Sandwich sandwichByName(@PathVariable String name) {
        for (Sandwich sandwich : sandwiches()) {
            if (sandwich.getName().toLowerCase().equals(name.toLowerCase())) {
                return sandwich;
            }
        }
        throw new SandwichNotFoundException();
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Sandwich saveSandwich(@RequestBody Sandwich sandwich) {
        sandwichRepository.save(sandwich);
        return sandwichByName(sandwich.getName());
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Sandwich updateSandwich(@PathVariable UUID id, @RequestBody Sandwich sandwich) {
        //Als deze id's verschillen -> waarsch hacker
        if (!id.equals(sandwich.getId())) {
            throw new SandwichNotFoundException();
        }
        //Optional want kan dat Sandwich niet aanwezig is
        Optional<Sandwich> maybeFoundSandwich = sandwichRepository.findById(id);
        if (maybeFoundSandwich.isPresent()) {
            Sandwich foundSandwich = maybeFoundSandwich.get();
            foundSandwich.setName(sandwich.getName());
            foundSandwich.setPrice(sandwich.getPrice());
            foundSandwich.setIngredients(sandwich.getIngredients());
            //Override huidige data (update)
            sandwichRepository.save(foundSandwich);
        } else {
            throw new SandwichNotFoundException();
        }
        return sandwich(sandwich.getId());
    }
}