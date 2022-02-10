package com.example.case6gr1be.service;

import com.example.case6gr1be.model.ServiceProvided;

import java.util.Optional;

public interface ServiceProvidedService {
    Iterable<ServiceProvided> findAll();
    Iterable<ServiceProvided> findAllByIdUser(Long id);
    void add(ServiceProvided serviceProvided);
}
