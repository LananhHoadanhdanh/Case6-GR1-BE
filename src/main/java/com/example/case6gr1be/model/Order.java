package com.example.case6gr1be.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp bookingTime;
    private Timestamp startTime;
    private Timestamp endTime;
    private double timeRent;

    public double getTimeRent() {
        return timeRent;
    }

    public void setTimeRent(double timeRent) {
        this.timeRent = timeRent;
    }

    public Order(Timestamp bookingTime, Timestamp startTime, Timestamp endTime, double timeRent, OrderStatus status, User provider, User renter) {
        this.bookingTime = bookingTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeRent = timeRent;
        this.status = status;
        this.provider = provider;
        this.renter = renter;
    }

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    private User renter;

    public Order() {
    }

    public Order(Timestamp bookingTime, Timestamp startTime, Timestamp endTime, OrderStatus status, User provider, User renter) {
        this.bookingTime = bookingTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.provider = provider;
        this.renter = renter;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
    }

    public Order(Timestamp startTime, Timestamp endTime, double timeRent, OrderStatus status, User provider, User renter) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeRent = timeRent;
        this.status = status;
        this.provider = provider;
        this.renter = renter;
    }

    public void setBookingTime(Timestamp bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getProvider() {
        return provider;
    }

    public void setProvider(User provider) {
        this.provider = provider;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
