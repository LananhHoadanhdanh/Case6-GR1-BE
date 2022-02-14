package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.Order;
import com.example.case6gr1be.model.SerProvided;
import com.example.case6gr1be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/users/{id}/orders")
    public ResponseEntity<Iterable<Order>> findAllOrderByRenter(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getAllOrderByRenter(id), HttpStatus.OK);
    }
}
