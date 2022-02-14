package com.example.case6gr1be.controller;

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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
public class OrderController {
    @Autowired
    private OrderService orderService;
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
        ArrayList<String> check = new ArrayList<>();

        order.setBookingTime(new Date());
        float s = order.getStartTime().getTime();
        float e = order.getEndTime().getTime();
        order.setTimeRent(((e - s) / 3600000));
        Iterable<ServiceProvided> provider = serviceProvidedService.findAllByIdUser(order.getProvider().getId());
        provider.forEach(new Consumer<ServiceProvided>() {
            @Override
            public void accept(ServiceProvided serviceProvided) {
                if (serviceProvided.getIdService() == 8) {
                    if (order.getStartTime().getTime() >= order.getBookingTime().getTime()
                            && ((order.getEndTime().getTime() - order.getStartTime().getTime()) >= 1800000)
                    ) {
                        orderService.save(order);
                        check.add("save done");
                    }
                }
                if (serviceProvided.getIdService() == 9) {
                    if (order.getStartTime().getTime() >= order.getBookingTime().getTime()
                            && ((order.getEndTime().getTime() - order.getStartTime().getTime()) >= 3600000)
                    ) {
                        orderService.save(order);
                        check.add("save done");
                    }
                }
            }
        });
        if (check.size() > 0) {
            return true;
        }
        return false;
    }

}
