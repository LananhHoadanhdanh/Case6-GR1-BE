package com.example.case6gr1be.service;

import com.example.case6gr1be.model.Order;
import org.springframework.data.repository.query.Param;

public interface OrderService extends GeneralService<Order>{
    Iterable<Order> getAllOrderByRenter(Long id);
}
