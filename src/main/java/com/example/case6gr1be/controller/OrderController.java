package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.*;
import com.example.case6gr1be.service.OrderService;
import com.example.case6gr1be.service.OrderStatusService;
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
}
