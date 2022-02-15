package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.Order;
import com.example.case6gr1be.repository.OrderRepository;
import com.example.case6gr1be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Iterable<Order> getAllOrderByRenter(Long id) {
        return orderRepository.getAllOrderByRenter(id);
    }

    @Override
    public Iterable<Order> getAllOrderByProvider(Long id) {
        return orderRepository.getAllOrderByProvider(id);
    }

    @Override
    public void removeOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
