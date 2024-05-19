package com.example.pfa2021.repository;

import com.example.pfa2021.entities.RelationSousCriter;
import com.example.pfa2021.entities.RelationSousCriterFahp;
import com.example.pfa2021.entities.SousCriter;
import com.example.pfa2021.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationSousCriterFahpRepository extends JpaRepository<RelationSousCriterFahp,Integer> {
    List<RelationSousCriterFahp> findBySousCriter1_Criter_ProjetAhp_Id(int projetAhpId);
    RelationSousCriterFahp findBySousCriter1AndSousCriter2(SousCriter sousCriter1, SousCriter sousCriter2);

}
