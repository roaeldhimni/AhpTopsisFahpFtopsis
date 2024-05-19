package com.example.pfa2021.repository;

import com.example.pfa2021.entities.RelationSousCriter;
import com.example.pfa2021.entities.SousCriter;
import com.example.pfa2021.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationSousCriterRepository extends JpaRepository<RelationSousCriter,Integer> {
    List<RelationSousCriter> findBySousCriter1_Criter_ProjetAhp_Id(int projetAhpId);
    RelationSousCriter findBySousCriter1AndSousCriter2(SousCriter sousCriter1, SousCriter sousCriter2);

}
