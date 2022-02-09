package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.ServiceProvided;
import com.example.case6gr1be.repository.ServiceProvidedRepository;
import com.example.case6gr1be.service.ServiceProvidedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ServiceProvidedImpl implements ServiceProvidedService {
    @Autowired
    private ServiceProvidedRepository serviceProvidedRepository;
    @Override
    public Iterable<ServiceProvided> findAll() {
        return serviceProvidedRepository.findAll();
    }

    @Override
    public Iterable<ServiceProvided> findAllByIdUser(Long id) {
        return serviceProvidedRepository.findAllByIdUser(id);
    }


    @Override
    public void add(ServiceProvided serviceProvided) {
        serviceProvidedRepository.save(serviceProvided);
    }
}
