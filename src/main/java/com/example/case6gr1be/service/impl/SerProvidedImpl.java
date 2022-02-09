package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.SerProvided;
import com.example.case6gr1be.service.SerProvidedService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SerProvidedImpl implements SerProvidedService {
    @Override
    public Iterable<SerProvided> findAll() {
        return null;
    }

    @Override
    public Optional<SerProvided> findById(Long id) {
        return Optional.empty();
    }

}
