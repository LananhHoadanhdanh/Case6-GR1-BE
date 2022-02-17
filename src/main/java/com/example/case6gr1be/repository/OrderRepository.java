package com.example.case6gr1be.repository;

import com.example.case6gr1be.model.Order;
import com.example.case6gr1be.model.User;
import com.example.case6gr1be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o order by o.id desc")
    Iterable<Order> findAllOrder();

    @Query("select o from Order o where o.renter.id = :id order by o.id desc")
    Iterable<Order> getAllOrderByRenter(@Param("id") Long id);

    @Query("select o from Order o where o.provider.id = :id order by o.id desc")
    Iterable<Order> getAllOrderByProvider(@Param("id") Long id);

    Iterable<Order> findAllByProvider(User user);

    @Query("select o from Order o where o.status.id = 3 order by o.id desc")
    Iterable<Order> getAllCompletedOrder();
}
