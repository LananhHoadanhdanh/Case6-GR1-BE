package com.example.case6gr1be.service;

import com.example.case6gr1be.model.Order;
import org.springframework.data.repository.query.Param;
import com.example.case6gr1be.model.User;

public interface OrderService extends GeneralService<Order>{
    Iterable<Order> getAllOrderByRenter(Long id);
    Iterable<Order> getAllOrderByProvider(Long id);
    void removeOrder(Long id);
    Iterable<Order> findAllByProvider(User user);
}
