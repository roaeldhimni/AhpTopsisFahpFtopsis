package com.example.pfa2021.entities;

import javax.persistence.*;

@Entity
public class Alternative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private int rank;
    private float ci;

    public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public float getCi() {
		return ci;
	}

	public void setCi(float ci) {
		this.ci = ci;
	}

	@ManyToOne
    private ProjetAhp projetAhpp ;

    public Alternative() {
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

    public ProjetAhp getProjetAhp() {
        return projetAhpp;
    }

    public void setProjetAhp(ProjetAhp projetAhp) {
        this.projetAhpp = projetAhp;
    }
}
