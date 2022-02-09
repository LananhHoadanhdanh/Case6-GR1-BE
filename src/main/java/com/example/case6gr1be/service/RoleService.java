package com.example.case6gr1be.service;


import com.example.case6gr1be.model.Role;

public interface RoleService {
    Iterable<Role> findAll();

    void save(Role role);

    Role findByName(String name);
}
