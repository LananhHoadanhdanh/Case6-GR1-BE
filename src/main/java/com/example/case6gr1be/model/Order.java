package com.example.case6gr1be.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Date;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date bookingTime;
    private Date startTime;
    private Date endTime;
    private double timeRent;

    public Order(Date startTime, OrderStatus status, User provider, User renter) {
        this.startTime = startTime;
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

    public Order(Date startTime, double timeRent, User provider, User renter) {
        this.timeRent = timeRent;
        this.provider = provider;
        this.renter = renter;
    }

    public Order(double timeRent, OrderStatus status, User provider, User renter) {
        this.timeRent = timeRent;
        this.status = status;
        this.provider = provider;
        this.renter = renter;
    }

    public Order(Date startTime, double timeRent, OrderStatus status, User provider, User renter) {
        this.startTime = startTime;
        this.timeRent = timeRent;
        this.status = status;
        this.provider = provider;
        this.renter = renter;
    }

    public Order(Date startTime, Date endTime, double timeRent, OrderStatus status, User provider, User renter) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeRent = timeRent;
        this.status = status;
        this.provider = provider;
        this.renter = renter;
    }

    public Order(Date bookingTime, Date startTime, Date endTime, double timeRent, OrderStatus status, User provider, User renter) {
        this.bookingTime = bookingTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeRent = timeRent;
        this.status = status;
        this.provider = provider;
        this.renter = renter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getTimeRent() {
        return timeRent;
    }

    public void setTimeRent(double timeRent) {
        this.timeRent = timeRent;
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
}
