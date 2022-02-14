package com.example.case6gr1be.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp bookingTime;
    private Timestamp startTime;
    private Timestamp endTime;
    private int status;

    private Long provider;

    private Long renter;

    public OrderDetail() {
    }

    public OrderDetail(Timestamp bookingTime, Timestamp startTime, Timestamp endTime, int status, Long provider, Long renter) {
        this.bookingTime = bookingTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.provider = provider;
        this.renter = renter;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getProvider() {
        return provider;
    }

    public void setProvider(Long provider) {
        this.provider = provider;
    }

    public Long getRenter() {
        return renter;
    }

    public void setRenter(Long renter) {
        this.renter = renter;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
