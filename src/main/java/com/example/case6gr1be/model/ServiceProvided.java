package com.example.case6gr1be.model;

import javax.persistence.*;

@Entity
public class ServiceProvided {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idService;
    private Long idUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public ServiceProvided(Long idService, Long idUser) {
        this.idService = idService;
        this.idUser = idUser;
    }

    public ServiceProvided() {
    }
}
