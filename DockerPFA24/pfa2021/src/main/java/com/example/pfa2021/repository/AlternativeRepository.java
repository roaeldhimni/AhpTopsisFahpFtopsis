package com.example.pfa2021.repository;

import com.example.pfa2021.entities.Alternative;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlternativeRepository extends JpaRepository<Alternative,Integer> {
	Alternative findByName(String alternative);
	List<Alternative> findByProjetAhppId(int projectId);
	//List<Alternative> findByProjetAhppId(Long projectId);
;}