package com.example.case6gr1be.repository;

import com.example.case6gr1be.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository< Order,Long> {

}
