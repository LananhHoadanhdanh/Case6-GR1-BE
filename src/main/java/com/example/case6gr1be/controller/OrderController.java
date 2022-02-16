package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.*;
import com.example.case6gr1be.service.OrderService;
import com.example.case6gr1be.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.case6gr1be.model.Order;
import com.example.case6gr1be.model.ServiceProvided;
import com.example.case6gr1be.service.OrderService;
import com.example.case6gr1be.service.ServiceProvidedService;
import com.example.case6gr1be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private UserService userService;
    @Autowired
    private ServiceProvidedService serviceProvidedService;

    @GetMapping("/orders")
    public ResponseEntity<Iterable<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public void save(@RequestBody Order order) {
        order.setBookingTime(new Date());
        orderService.save(order);
    }

    @PostMapping("/order")
    public boolean saveCheck(@RequestBody Order order) {
        Date sta = new Date();
        sta.setTime(order.getStartTime().getTime() - 25180000);
        order.setStartTime(sta);
        ArrayList<String> check = new ArrayList<>();
        Long time = (Long) Math.round(order.getTimeRent() * 3600000);
        Date end = new Date(time);
        end.setTime(order.getStartTime().getTime() + end.getTime());
        order.setBookingTime(new Date());
        order.setEndTime(end);
        float s = order.getStartTime().getTime();
        float e = order.getEndTime().getTime();
//        order.setTimeRent(((e - s) / 3600000));
        ArrayList<Order> orders = (ArrayList<Order>) orderService.findAllByProvider(order.getProvider());
        Iterable<ServiceProvided> provider = serviceProvidedService.findAllByIdUser(order.getProvider().getId());
        provider.forEach(new Consumer<ServiceProvided>() {
            @Override
            public void accept(ServiceProvided serviceProvided) {
                if (serviceProvided.getIdService() == 8) {
                    if (order.getStartTime().getTime() >= order.getBookingTime().getTime()
                            && ((order.getEndTime().getTime() - order.getStartTime().getTime()) >= 1800000)
                    ) {
                        if (orders.isEmpty()) {
                            orderService.save(order);
                            check.add("save done");
                        } else {
                            orders.forEach(new Consumer<Order>() {
                                @Override
                                public void accept(Order or) {
                                    if (order.getStartTime().getTime() > or.getStartTime().getTime() && order.getStartTime().getTime() > or.getEndTime().getTime()
                                            || order.getEndTime().getTime() < or.getStartTime().getTime() && order.getStartTime().getTime() < or.getStartTime().getTime()) {
                                        orderService.save(order);
                                        check.add("save done");
                                    }
                                }
                            });
                        }
                    }
                }
                if (serviceProvided.getIdService() == 9) {
                    if (order.getStartTime().getTime() >= order.getBookingTime().getTime()
                            && ((order.getEndTime().getTime() - order.getStartTime().getTime()) >= 3600000)
                    ) {
                        if (orders.isEmpty()) {
                            orderService.save(order);
                        } else {
                            orders.forEach(new Consumer<Order>() {
                                @Override
                                public void accept(Order or) {
                                    if (order.getStartTime().getTime() > or.getStartTime().getTime() && order.getStartTime().getTime() > or.getEndTime().getTime()
                                            || order.getEndTime().getTime() < or.getStartTime().getTime() && order.getStartTime().getTime() < or.getStartTime().getTime()) {
                                    } else {
                                        check.add("ss");
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });
        if (check.size() == 0) {
            orderService.save(order);
        }
        if (check.size() == 0) {
            return true;
        }
        return false;
    }

    @GetMapping("/renter/{id}/orders")
    public ResponseEntity<Iterable<Order>> findAllOrderByRenter(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getAllOrderByRenter(id), HttpStatus.OK);
    }

    @GetMapping("/provider/{id}/orders")
    public ResponseEntity<Iterable<Order>> findAllOrderByProvider(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getAllOrderByProvider(id), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Optional<Order>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/orders/{id}/changeStatus")
    public ResponseEntity<Order> changeStatus(@PathVariable Long id, Long statusId) {
        Order order = orderService.findById(id).get();
        OrderStatus orderStatus = orderStatusService.findById(statusId).get();
        order.setStatus(orderStatus);
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.removeOrder(id);
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.NO_CONTENT);

    }

    @GetMapping("/completedOrder")
    public ResponseEntity<Iterable<Order>> getAllCompletedOrder(){
        Iterable<Order> orders=orderService.getAllCompletedOrder();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

}
