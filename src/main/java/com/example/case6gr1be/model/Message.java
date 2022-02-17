package com.example.case6gr1be.model;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User idPro;
    @ManyToOne
    @JoinColumn(name = "renter_id")
    private User idUs;
    @Column(length = 1000)
    private String content;

    public User getIdPro() {
        return idPro;
    }

    public void setIdPro(User idPro) {
        this.idPro = idPro;
    }

    public User getIdUs() {
        return idUs;
    }

    public void setIdUs(User idUs) {
        this.idUs = idUs;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message(User idPro, User idUs, String content) {
        this.idPro = idPro;
        this.idUs = idUs;
        this.content = content;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
