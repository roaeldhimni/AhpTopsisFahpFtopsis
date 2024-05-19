package com.example.pfa2021.repository;

import com.example.pfa2021.entities.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert,Long> {

    Expert findById(int i);

	Expert findByEmail(String email);

	Expert findByUsernameAndPassword(String username,String password);}