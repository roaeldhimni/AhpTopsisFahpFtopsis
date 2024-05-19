package com.example.pfa2021.repository;

import com.example.pfa2021.entities.Alternative;
import com.example.pfa2021.entities.ExpertSousCriterAlternative;
import com.example.pfa2021.entities.SousCriter;
import com.example.pfa2021.entities.SousCriterAlternative;
import com.example.pfa2021.entities.SousCriterAlternativeFahp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpertSousCriterAlternativeRepository extends JpaRepository<ExpertSousCriterAlternative,Integer> {

	@Query("SELECT sca FROM SousCriterAlternativeFahp sca JOIN sca.alternative a WHERE a.id IN (SELECT alt.id FROM ProjetAhp p JOIN p.alternatives alt WHERE p.id = :projetId)")
	List<SousCriterAlternativeFahp> findByProjetId(@Param("projetId") int projetId);
	SousCriterAlternativeFahp findBySousCriterAndAlternative(SousCriter sousCriter1, Alternative alternative1);
	

}