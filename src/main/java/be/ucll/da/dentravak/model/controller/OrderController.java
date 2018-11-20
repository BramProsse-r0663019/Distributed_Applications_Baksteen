package be.ucll.da.dentravak.model.controller;

import be.ucll.da.dentravak.model.db.OrderRepository;
import be.ucll.da.dentravak.model.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/order", produces = "application/json")
public class OrderController {


    private final OrderRepository orderRepository;

    public OrderController(@Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //Default = /sandwiches & GET method
    @CrossOrigin
    @RequestMapping(value = "/orders")
    public List<Order> orders() {
        return (List<Order>) orderRepository.findAll();
    }

//    @RequestMapping(value = "/{id}")
//    //Sandwich meegeven in body en niet in path (@PathVariable)
//    public Order order(@PathVariable UUID id) {
//        Optional<Order> maybeFoundOrder = orderRepository.findById(id);
//        if(maybeFoundOrder.isPresent()) {
//            Order foundOrder = maybeFoundOrder.get();
//            return foundOrder;
//        }
//        throw new SandwichNotFoundException();
//    }

//    @RequestMapping(value = "/order/name/{name}")
//    //Sandwich meegeven in body en niet in path (@PathVariable)
//    public Sandwich sandwichByName(@PathVariable String name) {
//        for (Sandwich sandwich : sandwiches()) {
//            if (sandwich.getName().toLowerCase().equals(name.toLowerCase())) {
//                return  sandwich;
//            }
//        }
//        throw new SandwichNotFoundException();
//    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public Order saveOrder(@RequestBody Order order) {
//        orderRepository.save(order);
//        return order(order.getUuid());
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public Order updateOrder(@PathVariable UUID id, @RequestBody Order order) {
//        //Als deze id's verschillen -> waarsch hacker
//        if (!id.equals(order.getUuid())) {
//            throw new SandwichNotFoundException();
//        }
//        //Optional want kan dat Sandwich niet aanwezig is
//        Optional<Order> maybeFoundOrder = orderRepository.findById(id);
//        if(maybeFoundOrder.isPresent()) {
//            Order foundOrder = maybeFoundOrder.get();
//            foundOrder.setSandwichName(order.getSandwichName());
//            foundOrder.setSandwichPrice((order.getSandwichPrice()));
//            //Override huidige data (update)
//            orderRepository.save(foundOrder);
//        } else {
//            throw new SandwichNotFoundException();
//        }
//        return order(order.getUuid());
//    }

}
