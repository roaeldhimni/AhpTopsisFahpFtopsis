package com.example.pfa2021.controller;

import com.example.pfa2021.entities.Alternative;
import com.example.pfa2021.entities.Criter;
import com.example.pfa2021.entities.RelationCriter;
import com.example.pfa2021.repository.CriterRepository;
import com.example.pfa2021.repository.RelationCriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("relationCriters")
public class RelationCriterController {

    @Autowired
    private RelationCriterRepository relationCriterRepository ;
    @Autowired
    private CriterRepository criterRepository;
    @GetMapping("/all")
    public List<RelationCriter> findAll(){return relationCriterRepository.findAll(); }

    @PostMapping("/save")
    public RelationCriter save(@RequestBody RelationCriter relationCriter) {
        return relationCriterRepository.save(relationCriter);}
    
    @GetMapping("/projet/{projetAhpId}")
    public ResponseEntity<List<RelationCriter>> getRelationCriterByProjetAhpId(@PathVariable int projetAhpId) {
        List<RelationCriter> relationCriterList = relationCriterRepository.findByCriter1ProjetAhpIdOrCriter2ProjetAhpId(projetAhpId, projetAhpId);
        return ResponseEntity.ok(relationCriterList);}
    @PutMapping("/update/{criter1Id}/{criter2Id}")
    public ResponseEntity<?> updateRelationCriter(@PathVariable int criter1Id, @PathVariable int criter2Id, @RequestBody Float newValue) {
        // Récupérer les objets Criter correspondant aux identifiants
        Optional<Criter> criter1Optional = Optional.ofNullable(criterRepository.findById(criter1Id));
        Optional<Criter> criter2Optional = Optional.ofNullable(criterRepository.findById(criter2Id));

        if (!criter1Optional.isPresent() || !criter2Optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Récupérer les entités Criter
        Criter criter1 = criter1Optional.get();
        Criter criter2 = criter2Optional.get();

        // Recherchez la relation entre les deux critères
        RelationCriter relationCriter = relationCriterRepository.findByCriter1AndCriter2(criter1, criter2);
        if (relationCriter == null) {
            return ResponseEntity.notFound().build();
        }

        // Mettre à jour le facteur
        relationCriter.setFacteur(newValue);
        relationCriterRepository.save(relationCriter);

        return ResponseEntity.ok().build();}
    @PutMapping("/updateWeight/{criterId}")
    public ResponseEntity<Double> updateCriterWeight(@PathVariable Long criterId, @RequestBody Double poids) {
        // Trouver le critère par son ID
        Criter criter = criterRepository.findById(criterId.intValue());

        // Vérifier si le critère est trouvé
        if (criter == null) {
            // Si le critère n'est pas trouvé, retourner une réponse NotFound
            return ResponseEntity.notFound().build();
        }

        // Arrondir le poids à trois chiffres après la virgule
        double roundedPoids = Math.round(poids * 1000.0) / 1000.0;

        // Mettre à jour le poids du critère
        criter.setPoids(roundedPoids);
        criterRepository.save(criter);

        // Retourner le poids mis à jour
        return ResponseEntity.ok(criter.getPoids());
    }
}
