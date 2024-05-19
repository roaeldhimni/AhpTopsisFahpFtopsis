package com.example.pfa2021.repository;

import com.example.pfa2021.entities.Criter;
import com.example.pfa2021.entities.RelationCriter;
import com.example.pfa2021.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationCriterRepository extends JpaRepository<RelationCriter,Integer> {
	List<RelationCriter> findByCriter1ProjetAhpIdOrCriter2ProjetAhpId(int projetAhpId1, int projetAhpId2);
	RelationCriter findByCriter1AndCriter2(Criter criter1, Criter criter2);}
