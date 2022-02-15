package com.example.case6gr1be.service;

import com.example.case6gr1be.model.Order;
import com.example.case6gr1be.model.User;

public interface OrderService extends GeneralService<Order>{
    Iterable<Order> findAllByProvider(User user);
}
