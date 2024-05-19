package com.example.pfa2021.entities;
import javax.persistence.*;

@Entity
@Table(name = "souscriteralternativefahp")
public class SousCriterAlternativeFahp {

    @EmbeddedId
    private SousCriterAlternativePk id;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "lowerBound", column = @Column(name = "lower_bound")),
        @AttributeOverride(name = "midlbound", column = @Column(name = "middle_bound")),
        @AttributeOverride(name = "upperBound", column = @Column(name = "upper_bound"))
    })
    private FuzzyNumber facteur;

    @ManyToOne
    @JoinColumn(name = "sousCriter", referencedColumnName = "id", insertable = false, updatable = false)
    private SousCriter sousCriter;

    @ManyToOne
    @JoinColumn(name = "alternative", referencedColumnName = "id", insertable = false, updatable = false)
    private Alternative alternative;

	public SousCriterAlternativeFahp() {
	}
	
	public SousCriterAlternativeFahp(SousCriterAlternativePk id, double lowerBound, double midlbound, double upperBound) {
        this.id = id;
        this.facteur = new FuzzyNumber(lowerBound, midlbound, upperBound);
    }

	public SousCriterAlternativePk getId() {
		return id;
	}

	public void setId(SousCriterAlternativePk id) {
		this.id = id;
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

	public FuzzyNumber getFacteur() {
		return facteur;
	}

	public void setFacteur(FuzzyNumber facteur) {
		this.facteur=facteur;}}