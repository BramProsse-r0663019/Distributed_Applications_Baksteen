package be.ucll.da.dentravak.model.controller;

import be.ucll.da.dentravak.model.db.SandwichRepository;
import be.ucll.da.dentravak.model.domain.Sandwich;
import be.ucll.da.dentravak.model.domain.SandwichPreferences;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.naming.ServiceUnavailableException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
//import javax.inject.Inject;
//import javax.naming.ServiceUnavailableException;

@RestController
@RequestMapping(value = "/sandwiches", produces = "application/json")
public class SandwichController {

    private final SandwichRepository sandwichRepository;

    @Inject
    private DiscoveryClient discoveryClient;

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
            SandwichPreferences preferences = getPreferences("ronald.dehuysser@ucll.be");

            List<Sandwich> allSandwiches = (List<Sandwich>) sandwichRepository.findAll();

            Collections.sort(allSandwiches, new Comparator<Sandwich>(){
                @Override
                public int compare(Sandwich o1, Sandwich o2) {
                    if(preferences.getRatingForSandwich(o1.getId()) > preferences.getRatingForSandwich(o2.getId())){
                        return 1;
                    }
                    if(preferences.getRatingForSandwich(o1.getId()) < preferences.getRatingForSandwich(o2.getId())){
                        return -1;
                    }
                    return 0;
                }
            });

            return allSandwiches;
        } catch (ServiceUnavailableException e) {
            return (List<Sandwich>) sandwichRepository.findAll();
        } catch(Exception e){
            return (List<Sandwich>) sandwichRepository.findAll();
        }
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

    //
// why comment: for testing
    @GetMapping("/getpreferences/{emailAddress}")
    public SandwichPreferences getPreferences(@PathVariable String emailAddress) throws RestClientException, ServiceUnavailableException {
        URI service = recommendationServiceUrl()
                .map(s -> s.resolve("/recommend/" + emailAddress))
                .orElseThrow(ServiceUnavailableException::new);
        return restTemplate
                .getForEntity(service, SandwichPreferences.class)
                .getBody();
    }

//    public Optional<URI> recommendationServiceUrl() {
//        try {
//            return Optional.of(new URI("http://localhost:8081"));
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public Optional<URI> recommendationServiceUrl() {
        return discoveryClient.getInstances("recommendation")
                .stream()
                .map(si -> si.getUri())
                .findFirst();
    }
}
