package com.example.pfa2021.controller;

import com.example.pfa2021.entities.RelationCriter;
import com.example.pfa2021.entities.RelationSousCriter;
import com.example.pfa2021.entities.SousCriter;
import com.example.pfa2021.repository.RelationSousCriterRepository;
import com.example.pfa2021.repository.SousCriterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("relationSousCriters")
public class RelationSousCriterController {

    @Autowired
    private RelationSousCriterRepository relationSousCriterRepository ;
    @Autowired
    private SousCriterRepository sousCriterRepository;

    @GetMapping("/all")
    public List<RelationSousCriter> findAll(){return relationSousCriterRepository.findAll(); }

    @PostMapping("/save")
    public RelationSousCriter save(@RequestBody RelationSousCriter relationSousCriter) {
        return relationSousCriterRepository.save(relationSousCriter);}
    
    
    @GetMapping("/projet/{projetAhpId}/souscriteres")
    public ResponseEntity<List<Map<String, Object>>> getRelationSousCriteresByProjetAhpId(@PathVariable int projetAhpId) {
        List<RelationSousCriter> relationSousCriteresList = relationSousCriterRepository.findBySousCriter1_Criter_ProjetAhp_Id(projetAhpId);
        
        // Créez une liste pour stocker les objets modifiés
        List<Map<String, Object>> modifiedRelationSousCriteresList = new ArrayList<>();
        
        // Parcourez chaque relation de sous-critère pour la modification
        for (RelationSousCriter relation : relationSousCriteresList) {
            // Créez un nouvel objet pour stocker les sous-critères et le facteur
            Map<String, Object> modifiedRelation = new HashMap<>();
            
            // Stockez les sous-critères et le facteur dans l'objet modifié
            SousCriter sousCriter1 = relation.getSousCriter1();
            SousCriter sousCriter2 = relation.getSousCriter2();
            
            Map<String, Object> sousCriter1Map = new HashMap<>();
            sousCriter1Map.put("id", sousCriter1.getId());
            sousCriter1Map.put("name", sousCriter1.getName());
            sousCriter1Map.put("poids", sousCriter1.getPoids());
            sousCriter1Map.put("criter", sousCriter1.getCriter());
            
            Map<String, Object> sousCriter2Map = new HashMap<>();
            sousCriter2Map.put("id", sousCriter2.getId());
            sousCriter2Map.put("name", sousCriter2.getName());
            sousCriter2Map.put("poids", sousCriter2.getPoids());
            sousCriter2Map.put("criter", sousCriter2.getCriter());
            
            modifiedRelation.put("sousCriter1", sousCriter1Map);
            modifiedRelation.put("sousCriter2", sousCriter2Map);
            modifiedRelation.put("facteur", relation.getFacteur());
            
            // Ajoutez l'objet modifié à la liste
            modifiedRelationSousCriteresList.add(modifiedRelation);
        }
        
        // Renvoyez la liste modifiée contenant les objets avec les sous-critères complets
        return ResponseEntity.ok(modifiedRelationSousCriteresList);
    }

    @PutMapping("/update/{souscriter1}/{souscriter2}")
    public ResponseEntity<?> updateRelationCriterSCR(@PathVariable int souscriter1, @PathVariable int souscriter2, @RequestBody Float newValue) {
        // Récupérer les objets Criter correspondant aux identifiants
        Optional<SousCriter> criter1Optional = sousCriterRepository.findById(souscriter1);
        Optional<SousCriter> criter2Optional = sousCriterRepository.findById(souscriter2);

        if (!criter1Optional.isPresent() || !criter2Optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Récupérer les entités Criter
        SousCriter sousCriter1 = criter1Optional.get();
        SousCriter sousCriter2 = criter2Optional.get();

        // Recherchez la relation entre les deux critères
        RelationSousCriter relationSousCriter = relationSousCriterRepository.findBySousCriter1AndSousCriter2(sousCriter1, sousCriter2);
        if (relationSousCriter == null) {
            return ResponseEntity.notFound().build();
        }

        // Mettre à jour le facteur
        relationSousCriter.setFacteur(newValue);
        relationSousCriterRepository.save(relationSousCriter);

        return ResponseEntity.ok().build();}

}
