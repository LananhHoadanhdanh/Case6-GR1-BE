package com.example.case6gr1be.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class SerProvinder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "service_provinder",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> projects;
    public SerProvinder() {
    }

    public SerProvinder(String name) {
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
