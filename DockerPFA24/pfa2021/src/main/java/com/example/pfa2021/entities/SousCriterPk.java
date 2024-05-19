package com.example.pfa2021.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SousCriterPk implements Serializable {

    private int sousCriter1 ;
    private int sousCriter2 ;

    public SousCriterPk() {
    }

    public int getSousCriter1() {
        return sousCriter1;
    }

    public void setSousCriter1(int sousCriter1) {
        this.sousCriter1 = sousCriter1;
    }

    public int getSousCriter2() {
        return sousCriter2;
    }

    public void setSousCriter2(int sousCriter2) {
        this.sousCriter2 = sousCriter2;
    }
}
