package com.example.case6gr1be.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Collection;

public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> roles;
    StatusUser status;

    public JwtResponse(String accessToken, Long id, String username, Collection<? extends GrantedAuthority> roles, StatusUser status) {
        this.token = accessToken;
        this.username = username;
        this.roles = roles;
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public StatusUser getStatus() {
        return status;
    }

    public void setStatus(StatusUser status) {
        this.status = status;
    }
}
