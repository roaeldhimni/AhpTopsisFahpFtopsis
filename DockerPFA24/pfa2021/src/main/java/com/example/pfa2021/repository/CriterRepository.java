package com.example.pfa2021.repository;

import com.example.pfa2021.entities.Criter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CriterRepository extends JpaRepository<Criter,Integer> {

    Criter findById(int criterId );

	Criter findByName(String criterName);

	List<Criter> findByProjetAhpId(int projetAhpId);

	//List<Criter> findByProjetAhpId(Long projectId);
	
}
