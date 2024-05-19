package com.example.pfa2021.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pfa2021.entities.CriterPK;
import com.example.pfa2021.entities.RelationCriterFahp;
import com.example.pfa2021.entities.RelationSousCriterFahp;
import com.example.pfa2021.entities.SousCriterPk;
import com.example.pfa2021.repository.RelationSousCriterFahpRepository;

import java.util.List;

@RestController
@RequestMapping("/relationSousCriterFahp")
public class RelationSousCriterFahpController {

    @Autowired
    private RelationSousCriterFahpRepository relationSousCriterFahpRepository;

    // Endpoint pour récupérer toutes les relations
    @GetMapping("/all")
    public List<RelationSousCriterFahp> findAll() {
        return relationSousCriterFahpRepository.findAll();
    }

    // Endpoint pour enregistrer une nouvelle relation
    @PostMapping("/save")
    public RelationSousCriterFahp save(@RequestBody RelationSousCriterFahp relationSousCriterFahp) {
    	
    	SousCriterPk id = relationSousCriterFahp.getId(); // Récupérez l'identifiant
        double lowerBound = relationSousCriterFahp.getFacteur().getLowerBound();
        double midlbound = relationSousCriterFahp.getFacteur().getMidlbound();
        double upperBound = relationSousCriterFahp.getFacteur().getUpperBound();

        // Créez une nouvelle instance en passant l'identifiant et les valeurs FuzzyNumber au constructeur
        RelationSousCriterFahp newRelationSousCriterFahp = new RelationSousCriterFahp(id, lowerBound, midlbound, upperBound);

    	
        return relationSousCriterFahpRepository.save(newRelationSousCriterFahp);
    }
    

}
