package com.example.case6gr1be.repository;

import com.example.case6gr1be.model.ServiceProvided;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided,Long> {
Iterable<ServiceProvided> findAllByIdUser(Long id);
}
