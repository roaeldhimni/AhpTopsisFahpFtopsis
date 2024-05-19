package com.example.pfa2021.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Criter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private int numbresousCriters ;
    private double poids ;

    private double CR;

    @ManyToOne
    private ProjetAhp projetAhp ;

    @JsonIgnore
    @OneToMany(mappedBy ="criter", fetch = FetchType.EAGER)
    private List<SousCriter> sousCriters ;

    public Criter() {
    }

    
    public double getCR() {
		return CR;
	}


	public void setCR(double cR) {
		CR = cR;
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbresousCriters() {
        return numbresousCriters;
    }

    public void setNumbresousCriters(int numbresousCriters) {
        this.numbresousCriters = numbresousCriters;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public ProjetAhp getProjetAhp() {
        return projetAhp;
    }

    public void setProjetAhp(ProjetAhp projetAhp) {
        this.projetAhp = projetAhp;
    }
    public List<SousCriter> getSousCriters() {
        return sousCriters;
    }

    public void setSousCriters(List<SousCriter> sousCriters) {
        this.sousCriters = sousCriters;
    }
}
