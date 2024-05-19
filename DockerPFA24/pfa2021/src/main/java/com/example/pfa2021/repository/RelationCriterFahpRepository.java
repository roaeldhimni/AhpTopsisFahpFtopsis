package com.example.pfa2021.repository;

import com.example.pfa2021.entities.Criter;
import com.example.pfa2021.entities.RelationCriterFahp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationCriterFahpRepository extends JpaRepository<RelationCriterFahp, Long> {
    List<RelationCriterFahp> findByCriter1ProjetAhpIdOrCriter2ProjetAhpId(int projetAhpId1, int projetAhpId2);

    RelationCriterFahp findByCriter1AndCriter2(Criter criter1, Criter criter2);
}
