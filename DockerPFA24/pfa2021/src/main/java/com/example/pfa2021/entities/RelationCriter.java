package com.example.pfa2021.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
public class RelationCriter implements Serializable {

    @EmbeddedId
    private CriterPK id ;
    private float facteur ;
    
    @ManyToOne
    @JoinColumn(name="criter1", referencedColumnName = "id", insertable = false , updatable = false)
    private Criter criter1;

    @ManyToOne
    @JoinColumn(name="criter2", referencedColumnName = "id", insertable = false , updatable = false)
    private Criter criter2;

    public RelationCriter() {
    }

    public CriterPK getId() {
        return id;
    }

    public void setId(CriterPK id) {
        this.id = id;
    }

    public float getFacteur() {
        return facteur;
    }

    public void setFacteur(float facteur) {
        this.facteur = facteur;
    }

    public Criter getCriter1() {
        return criter1;
    }

    public void setCriter1(Criter criter1) {
        this.criter1 = criter1;
    }

    public Criter getCriter2() {
        return criter2;
    }

    public void setCriter2(Criter criter2) {
        this.criter2 = criter2;
    }
}
