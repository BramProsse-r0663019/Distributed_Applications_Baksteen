package be.ucll.da.dentravak.model.controller;

import be.ucll.da.dentravak.model.db.OrderRepository;
import be.ucll.da.dentravak.model.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/orders", produces = "application/json")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(@Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @CrossOrigin
    @GetMapping
    public List<Order> orders(@RequestParam(value="date", required=false) String date) {
        List<Order> allOrders = (List<Order>) orderRepository.findAll();
        List<Order> ordersOfDate = new ArrayList<>();

        try {
            LocalDate creationDate = LocalDate.parse(date);
            for (Order order : allOrders) {
                LocalDate dateOfOrder = order.getCreationDate().toLocalDate();
                if(dateOfOrder.isEqual(creationDate)) {
                    ordersOfDate.add(order);
                }
            }
            return ordersOfDate;
        } catch (Exception e) {
            return (List<Order>) orderRepository.findAll();
        }
    }

    // @CrossOrigin
    // //@GetMapping
    // public List<Order> ordersByDate(@RequestParam(value="date", required=true) String date) {
    //     List<Order> allOrders = orders();
    //     List<Order> orders = new ArrayList<>();

    //     LocalDate creationDate = LocalDate.parse(date);
    //     for (Order order : allOrders) {
    //         LocalDate dateOfOrder = order.getCreationDate().toLocalDate();
    //         if(dateOfOrder.isEqual(creationDate)) {
    //             orders.add(order);
    //         }
    //     }
    //     return orders;
    // }

    @CrossOrigin
    @GetMapping(value = "/today")
    public List<Order> ordersOfToday() {
        List<Order> allOrders = orders();
        List<Order> todaysOrders = new ArrayList<>();

        for (Order order : allOrders) {
            //Check for orders today
            LocalDate date = order.getCreationDate().toLocalDate();
            if(date.isEqual(LocalDate.now())) {
                todaysOrders.add(order);
            }   
        }
        return todaysOrders;
    }

    @CrossOrigin
    @RequestMapping(value = "/print")
    public List<Order> printOrderOfToday() {
        List<Order> allOrders = orders();
        List<Order> todaysOrders = new ArrayList<>();

        for (Order order : allOrders) {
            //Check for orders today
            LocalDate date = order.getCreationDate().toLocalDate();
            if(date.isEqual(LocalDate.now())) {
                order.setPrinted(true);
                orderRepository.save(order);
                todaysOrders.add(order);
            }
        }
        return todaysOrders;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Order saveOrder(@RequestBody Order order) {
        orderRepository.save(order);
        return order;
    }
}