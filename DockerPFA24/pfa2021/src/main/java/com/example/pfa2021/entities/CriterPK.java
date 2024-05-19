package com.example.pfa2021.entities;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Embeddable
public class CriterPK implements Serializable {

    private int criter1 ;
    private int criter2 ;

    public CriterPK() {
    }

    public int getCriter1() {
        return criter1;
    }

    public void setCriter1(int criter1) {
        this.criter1 = criter1;
    }

    public int getCriter2() {
        return criter2;
    }

    public void setCriter2(int criter2) {
        this.criter2 = criter2;
    }
}
