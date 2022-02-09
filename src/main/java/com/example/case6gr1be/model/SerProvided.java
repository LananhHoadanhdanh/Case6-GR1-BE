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
    private int status;
    private int category;
    @ManyToMany
    @JoinTable(name = "service_provided",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> projects;
    public SerProvided() {
    }

    public SerProvided(String name, int status, int category, Collection<User> projects) {
        this.name = name;
        this.status = status;
        this.category = category;
        this.projects = projects;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Collection<User> getProjects() {
        return projects;
    }

    public void setProjects(Collection<User> projects) {
        this.projects = projects;
    }
}
