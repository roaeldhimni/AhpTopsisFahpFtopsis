package com.example.pfa2021.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SousCriterAlternativePk implements Serializable {

    private int sousCriter ;
    private  int alternative ;

    public SousCriterAlternativePk() {
    }

    public int getSousCriter() {
        return sousCriter;
    }

    public void setSousCriter(int sousCriter) {
        this.sousCriter = sousCriter;
    }

    public int getAlternative() {
        return alternative;
    }

    public void setAlternative(int alternative) {
        this.alternative = alternative;
    }
}
