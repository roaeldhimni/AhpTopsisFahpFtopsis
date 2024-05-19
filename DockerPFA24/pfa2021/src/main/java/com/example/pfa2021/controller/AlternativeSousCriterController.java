package com.example.pfa2021.controller;

import com.example.pfa2021.entities.Alternative;
import com.example.pfa2021.entities.SousCriter;
import com.example.pfa2021.entities.SousCriterAlternative;
import com.example.pfa2021.repository.AlternativeRepository;
import com.example.pfa2021.repository.AlternativeSousCriterRepository;
import com.example.pfa2021.repository.SousCriterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("alternativesSousCriters")
public class AlternativeSousCriterController {

    @Autowired
    private AlternativeSousCriterRepository alternativeSousCriterRepoitory ;
    @Autowired
    private AlternativeRepository alternativeRepository;
    @Autowired
    private SousCriterRepository sousCriterRepository;

    @GetMapping("/all")
    public List<SousCriterAlternative> findAll(){return alternativeSousCriterRepoitory.findAll(); }

    @PostMapping("/save")
    public SousCriterAlternative save(@RequestBody SousCriterAlternative sousCriterAlternative) {
        return alternativeSousCriterRepoitory.save(sousCriterAlternative);}
    
    @GetMapping("/projet/{projetId}")
    public List<SousCriterAlternative> findByProjetId(@PathVariable("projetId") int projetId) {
        return alternativeSousCriterRepoitory.findByProjetId(projetId);}
    @PutMapping("/update/{souscriter}/{alternative}")
    public ResponseEntity<?> updateRelationCriterSCRAlt(@PathVariable int souscriter, @PathVariable int alternative, @RequestBody int newValue) {
        // Récupérer les objets Criter correspondant aux identifiants
        Optional<SousCriter> criter1Optional = sousCriterRepository.findById(souscriter);
        Optional<Alternative> criter2Optional = alternativeRepository.findById(alternative);

        if (!criter1Optional.isPresent() || !criter2Optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Récupérer les entités Criter
        SousCriter sousCriter1 = criter1Optional.get();
        Alternative alternative1 = criter2Optional.get();

        // Recherchez la relation entre les deux critères
        SousCriterAlternative relationSousCriterAlternative = alternativeSousCriterRepoitory.findBySousCriterAndAlternative(sousCriter1, alternative1);
        if (relationSousCriterAlternative == null) {
            return ResponseEntity.notFound().build();
        }

        // Mettre à jour le facteur
        relationSousCriterAlternative.setFacteur(newValue);
        alternativeSousCriterRepoitory.save(relationSousCriterAlternative);

        return ResponseEntity.ok().build();}}