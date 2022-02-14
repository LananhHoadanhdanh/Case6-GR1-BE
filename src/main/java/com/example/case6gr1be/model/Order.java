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
    private Date bookingTime;
    private Date startTime;
    private Date endTime;
    private double timeRent;

    public Order(Date startTime, Date endTime, double timeRent) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeRent = timeRent;
    }

    public Order(Date bookingTime, Date startTime, Date endTime, double timeRent) {
        this.bookingTime = bookingTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeRent = timeRent;
    }

    public Order() {
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
}
