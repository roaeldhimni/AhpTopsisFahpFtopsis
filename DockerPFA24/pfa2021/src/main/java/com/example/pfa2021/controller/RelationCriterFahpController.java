package com.example.pfa2021.controller;

import com.example.pfa2021.entities.Criter;
import com.example.pfa2021.entities.CriterPK;
import com.example.pfa2021.entities.RelationCriterFahp;
import com.example.pfa2021.entities.FuzzyNumber;
import com.example.pfa2021.repository.RelationCriterFahpRepository;
import com.example.pfa2021.repository.CriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/relationCriterFahp")
public class RelationCriterFahpController {

    @Autowired
    private RelationCriterFahpRepository relationCriterFahpRepository;

    @Autowired
    private CriterRepository criterRepository;

    @GetMapping("/all")
    public List<RelationCriterFahp> findAll() {
        return relationCriterFahpRepository.findAll();
    }

 // Par exemple, dans votre méthode save du contrôleur
    @PostMapping("/save")
    public RelationCriterFahp save(@RequestBody RelationCriterFahp relationCriterFahp) {
        CriterPK id = relationCriterFahp.getId(); // Récupérez l'identifiant
        double lowerBound = relationCriterFahp.getFacteur().getLowerBound();
        double midlbound = relationCriterFahp.getFacteur().getMidlbound();
        double upperBound = relationCriterFahp.getFacteur().getUpperBound();

        // Créez une nouvelle instance en passant l'identifiant et les valeurs FuzzyNumber au constructeur
        RelationCriterFahp newRelationCriterFahp = new RelationCriterFahp(id, lowerBound, midlbound, upperBound);

        // Sauvegardez cette nouvelle instance
        return relationCriterFahpRepository.save(newRelationCriterFahp);
    }


}
