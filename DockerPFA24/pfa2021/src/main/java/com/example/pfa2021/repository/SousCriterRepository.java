package com.example.pfa2021.repository;

import com.example.pfa2021.entities.RelationSousCriter;
import com.example.pfa2021.entities.SousCriter;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SousCriterRepository extends JpaRepository<SousCriter,Integer>{

	SousCriter findByName(String criterName);

    List<SousCriter> findByCriterId(int criterId);

     Optional<SousCriter> findById(int id);



    }
