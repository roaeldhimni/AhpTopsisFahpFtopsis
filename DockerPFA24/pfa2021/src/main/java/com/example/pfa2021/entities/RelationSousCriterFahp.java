package com.example.pfa2021.entities;

import javax.persistence.*;

@Entity
@Table(name = "relationsouscriterfahp")
public class RelationSousCriterFahp {
    @EmbeddedId
    private SousCriterPk id;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "lowerBound", column = @Column(name = "lower_bound")),
        @AttributeOverride(name = "midlbound", column = @Column(name = "midlbound")),
        @AttributeOverride(name = "upperBound", column = @Column(name = "upper_bound"))
    })
    private FuzzyNumber facteur;

    @ManyToOne
    @JoinColumn(name="sousCriter1", referencedColumnName = "id", insertable = false , updatable = false)
    private SousCriter sousCriter1;

    @ManyToOne
    @JoinColumn(name="sousCriter2", referencedColumnName = "id", insertable = false , updatable = false)
    private SousCriter sousCriter2;

	
	 public RelationSousCriterFahp(SousCriterPk id, double lowerBound, double midlbound, double upperBound) {
	        this.id = id;
	        this.facteur = new FuzzyNumber(lowerBound, midlbound, upperBound);
	    }
	public RelationSousCriterFahp() {}

	public SousCriterPk getId() {
		return id;
	}

	public void setId(SousCriterPk id) {
		this.id = id;
	}

	public FuzzyNumber getFacteur() {
		return facteur;
	}

	public void setFacteur(FuzzyNumber facteur) {
		this.facteur = facteur;
	}

	public SousCriter getSousCriter1() {
		return sousCriter1;
	}

	public void setSousCriter1(SousCriter sousCriter1) {
		this.sousCriter1 = sousCriter1;
	}

	public SousCriter getSousCriter2() {
		return sousCriter2;
	}

	public void setSousCriter2(SousCriter sousCriter2) {
		this.sousCriter2 = sousCriter2;
	}
	

   
}
