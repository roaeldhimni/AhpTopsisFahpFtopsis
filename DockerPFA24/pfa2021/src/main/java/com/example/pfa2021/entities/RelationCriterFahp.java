package com.example.pfa2021.entities;

import javax.persistence.*;

@Entity
@Table(name = "relationcriterfahp")
public class RelationCriterFahp {
    @EmbeddedId
    private CriterPK id ;
    @Embedded
   // @Column(nullable = true) // Ou ne pas spécifier nullable, car true est la valeur par défaut
    @AttributeOverrides({
        @AttributeOverride(name = "lowerBound", column = @Column(name = "lower_bound")),
        @AttributeOverride(name = "midlbound", column = @Column(name = "midlbound")),
        @AttributeOverride(name = "upperBound", column = @Column(name = "upper_bound"))
    })
    private FuzzyNumber facteur;


    @ManyToOne
    @JoinColumn(name="criter1", referencedColumnName = "id", insertable = false , updatable = false)
    private Criter criter1;

    @ManyToOne
    @JoinColumn(name="criter2", referencedColumnName = "id", insertable = false , updatable = false)
    private Criter criter2;

    public RelationCriterFahp(CriterPK id, double lowerBound, double midlbound, double upperBound) {
        this.id = id;
        this.facteur = new FuzzyNumber(lowerBound, midlbound, upperBound);
    }





    public RelationCriterFahp() {
		
	}




	public FuzzyNumber getFacteur() {
		return facteur;
	}




	public void setFacteur(FuzzyNumber facteur) {
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




	public CriterPK getId() {
        return id;
    }

    public void setId(CriterPK id) {
       this.id=id;}}