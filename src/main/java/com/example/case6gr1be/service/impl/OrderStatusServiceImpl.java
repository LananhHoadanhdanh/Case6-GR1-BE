package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.OrderStatus;
import com.example.case6gr1be.repository.OrderStatusRepository;
import com.example.case6gr1be.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public Iterable<OrderStatus> findAll() {
        return orderStatusRepository.findAll();
    }

    @Override
    public Optional<OrderStatus> findById(Long id) {
        return orderStatusRepository.findById(id);
    }

    @Override
    public void save(OrderStatus orderStatus) {

    }
}
