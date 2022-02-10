package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.StatusUser;
import com.example.case6gr1be.repository.StatusUserRepository;
import com.example.case6gr1be.service.StatusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusUserImpl implements StatusUserService {
    @Autowired
    StatusUserRepository statusUserRepository;

    @Override
    public Iterable<StatusUser> findAll() {
        return statusUserRepository.findAll();
    }

    @Override
    public Optional<StatusUser> findById(Long id) {
        return statusUserRepository.findById(id);
    }

    @Override
    public void save(StatusUser statusUser) {

    }
}
