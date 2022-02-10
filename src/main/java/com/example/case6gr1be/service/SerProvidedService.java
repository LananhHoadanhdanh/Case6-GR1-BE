package com.example.case6gr1be.service;

import com.example.case6gr1be.model.SerProvided;

import java.util.Optional;

public interface SerProvidedService {
     Iterable<SerProvided> findAll();
    Optional<SerProvided> findById(Long id);
//    void activeService(Long id,int idService);
}
