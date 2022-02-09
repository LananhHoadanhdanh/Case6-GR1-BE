package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.SerProvinder;
import com.example.case6gr1be.service.SerProvinderService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SerProvinderImpl implements SerProvinderService {
    @Override
    public Iterable<SerProvinder> findAll() {
        return null;
    }

    @Override
    public Optional<SerProvinder> findById(Long id) {
        return Optional.empty();
    }

}
