package be.ucll.da.dentravak.controllers;


import be.ucll.da.dentravak.model.Application;
import be.ucll.da.dentravak.model.db.SandwichRepository;
import be.ucll.da.dentravak.model.domain.Sandwich;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SandwichControllerIntegrationTest extends be.ucll.da.dentravak.controllers.AbstractControllerIntegrationTest {
    @Autowired
    private SandwichRepository sandwichRepository;

    @Before
    public void cleanRepository() {
        sandwichRepository.deleteAll();
    }


    @Test
    public void testASingleSandwich(){
        Sandwich sandwich = getASingleSandwich();

        String json = httpPost("/sandwiches", sandwich);



        assertThatJson(json).node("name").isEqualTo("BroodjeHesp");
        assertThatJson(json).node("price").isEqualTo(3.2);
        assertThatJson(json).node("ingredients").isEqualTo("Brood, kip, curry, mayo");
    }

    @Test
    public void identicalSandwichesHaveTheSameId(){
        Sandwich sandwich = getASingleSandwich();

        String json = httpPost("/sandwiches", sandwich);
        String sjson = httpPost("/sandwiches", sandwich);


        assertThatJson(json).isEqualTo(sjson);
    }


    @Test
    public void requestSandwichByName(){
        Sandwich sandwich = getASingleSandwich();
        sandwich.setName("KipcurryExtra");


        String json = httpPost("/sandwiches", sandwich);

        String rjson = httpGet("/sandwiches/name/" + sandwich.getName());

        assertThatJson(json).isEqualTo(rjson);

    }

    public Sandwich getASingleSandwich(){
        return new Sandwich("BroodjeHesp",new BigDecimal(3.2), "Brood, kip, curry, mayo" );
    }

}
