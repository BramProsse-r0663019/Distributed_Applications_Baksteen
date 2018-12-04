package be.ucll.da.dentravak.model.controller;

import be.ucll.da.dentravak.model.db.OrderRepository;
import be.ucll.da.dentravak.model.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @RequestMapping(value = "")
    public List<Order> orders() {
        return (List<Order>) orderRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "?date={creationdate}")
    public List<Order> ordersByDate(@PathVariable LocalDate creationDate) {
        List<Order> allOrders = orders();
        List<Order> orders = new ArrayList<>();

        for (Order order : allOrders) {
            if(order.getCreationDate().equals(creationDate)) {
                orders.add(order);
            }
        }
        return orders;
    }

    @CrossOrigin
    @RequestMapping(value = "/today")
    public List<Order> ordersOfToday() {
        List<Order> allOrders = orders();
        List<Order> todaysOrders = new ArrayList<>();

        for (Order order : allOrders) {
            //Check for orders today
            if(order.getCreationDate().getYear() == LocalDate.now().getYear() && order.getCreationDate().getMonth() == LocalDate.now().getMonth() && order.getCreationDate().getDayOfYear() == LocalDate.now().getDayOfYear()) {
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
            if(order.getCreationDate().getYear() == LocalDate.now().getYear() && order.getCreationDate().getMonth() == LocalDate.now().getMonth() && order.getCreationDate().getDayOfYear() == LocalDate.now().getDayOfYear()) {
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