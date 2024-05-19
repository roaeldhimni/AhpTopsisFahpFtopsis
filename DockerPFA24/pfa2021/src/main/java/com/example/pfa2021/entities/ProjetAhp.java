package com.example.pfa2021.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProjetAhp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private int numbreCriters ;
    private double cr ;
    private int numbreAlternatives ;
    private String methode; 
    @ManyToOne
    private User user ;

    @JsonIgnore
    @OneToMany(mappedBy ="projetAhp", fetch = FetchType.LAZY)
    private List<Criter> criters ;


    @JsonIgnore
    @OneToMany(mappedBy ="projetAhpp", fetch = FetchType.LAZY)
    private List<Alternative> alternatives ;

    public ProjetAhp() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethode() {
		return methode;
	}

	public void setMethode(String methode) {
		this.methode = methode;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbreCriters() {
        return numbreCriters;
    }

    public void setNumbreCriters(int numbreCriters) {
        this.numbreCriters = numbreCriters;
    }

  public List<Criter> getCriters() {
        return criters;
    }

    public void setCriters(List<Criter> criters) {
        this.criters = criters;
    }

    public double getCr() {
        return cr;
    }

    public void setCr(double cr) {
        this.cr = cr;
    }

    public int getNumbreAlternatives() {
        return numbreAlternatives;
    }

    public void setNumbreAlternatives(int numbreAlternatives) {
        this.numbreAlternatives = numbreAlternatives;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
       this.user=user;}}