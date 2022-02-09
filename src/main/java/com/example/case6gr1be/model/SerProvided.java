package com.example.case6gr1be.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class SerProvided {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int category;

    public SerProvided() {
    }

    public SerProvided(String name, int status, int category, Collection<User> users) {
        this.name = name;
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public SerProvided(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
