package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.Order;
import com.example.case6gr1be.model.SerProvided;
import com.example.case6gr1be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.case6gr1be.model.Order;
import com.example.case6gr1be.service.OrderService;
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
import java.util.Date;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/orders")
    public ResponseEntity<Iterable<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public void save(@RequestBody Order order) {
        orderService.save(order);
    }

    @PostMapping("/order")
    public void saveCheck(@RequestBody Order order) {
        if (order.getStartTime().getTime() >= order.getBookingTime().getTime()
                && (order.getEndTime().getTime() - order.getStartTime().getTime()>=3600001)) {
            orderService.save(order);
        }
    }

    @GetMapping("/users/{id}/orders")
    public ResponseEntity<Iterable<Order>> findAllOrderByRenter(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getAllOrderByRenter(id), HttpStatus.OK);
    }
}
