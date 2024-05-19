package com.example.pfa2021.repository;

import com.example.pfa2021.entities.Criter;
import com.example.pfa2021.entities.ProjetAhp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProjetAhpRepository extends JpaRepository<ProjetAhp,Integer> {

    @Query(value = "FROM Criter c WHERE c.projetAhp.id= :id")
    List<Criter> getcriters( @Param("id") int id );

    

    @Query("SELECT MAX(p.id) FROM ProjetAhp p")
    Long getLatestProjectId();
}
