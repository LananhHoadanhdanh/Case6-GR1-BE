package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.SerProvided;
import com.example.case6gr1be.repository.SerProvidedRepository;
import com.example.case6gr1be.service.SerProvidedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SerProvidedImpl implements SerProvidedService {
    @Autowired
    private SerProvidedRepository serProvidedRepository;
    @Override
    public Iterable<SerProvided> findAll() {
        return serProvidedRepository.findAll() ;
    }

    @Override
    public Optional<SerProvided> findById(Long id) {
        return Optional.empty();
    }

}
