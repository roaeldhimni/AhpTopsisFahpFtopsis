package com.example.pfa2021.repository;

import com.example.pfa2021.entities.Alternative;
import com.example.pfa2021.entities.SousCriter;
import com.example.pfa2021.entities.SousCriterAlternative;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlternativeSousCriterRepository extends JpaRepository<SousCriterAlternative,Integer> {

	@Query("SELECT sca FROM SousCriterAlternative sca JOIN sca.alternative a WHERE a.id IN (SELECT alt.id FROM ProjetAhp p JOIN p.alternatives alt WHERE p.id = :projetId)")
	List<SousCriterAlternative> findByProjetId(@Param("projetId") int projetId);
	SousCriterAlternative findBySousCriterAndAlternative(SousCriter sousCriter1, Alternative alternative1);}