package com.example.pfa2021.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
public class SousCriterAlternative implements Serializable {

    @EmbeddedId
    private SousCriterAlternativePk id ;

    private float facteur ;

    @ManyToOne

    @JoinColumn(name="sousCriter", referencedColumnName = "id", insertable = false , updatable = false)
    private SousCriter sousCriter ;
    @ManyToOne

    @JoinColumn(name="alternative", referencedColumnName = "id", insertable = false , updatable = false)
    private Alternative alternative ;

    public SousCriterAlternative() {
    }

    public SousCriterAlternativePk getId() {
        return id;
    }

    public void setId(SousCriterAlternativePk id) {
        this.id = id;
    }

    public float getFacteur() {
        return facteur;
    }

    public void setFacteur(float facteur) {
        this.facteur = facteur;
    }

    public SousCriter getSousCriter() {
        return sousCriter;
    }

    public void setSousCriter(SousCriter sousCriter) {
        this.sousCriter = sousCriter;
    }

    public Alternative getAlternative() {
        return alternative;
    }

    public void setAlternative(Alternative alternative) {
        this.alternative = alternative;
    }
}
