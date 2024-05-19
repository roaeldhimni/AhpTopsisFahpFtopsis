package com.example.pfa2021.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String username ;
    private String password ;
    private String email ;

    @JsonIgnore
    @OneToMany(mappedBy ="user", fetch = FetchType.LAZY)
    private List<ProjetAhp> projetAhps;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProjetAhp> getProjetAhps() {
        return projetAhps;
    }

    public void setProjetAhps(List<ProjetAhp> projetAhps) {
        this.projetAhps = projetAhps;
    }
}
