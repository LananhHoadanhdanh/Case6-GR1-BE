package com.example.case6gr1be.service;

import com.example.case6gr1be.model.SerProvinder;
import com.example.case6gr1be.model.User;

import java.util.Optional;

public interface SerProvinderService {
     Iterable<SerProvinder> findAll();
    Optional<SerProvinder> findById(Long id);
}
