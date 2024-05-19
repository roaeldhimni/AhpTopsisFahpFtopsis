package com.example.pfa2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pfa2021.entities.SousCriterAlternativeFahp;
import com.example.pfa2021.entities.SousCriterAlternativePk;
import com.example.pfa2021.repository.AlternativeSousCriterFahpRepository;

import java.util.List;

@RestController
@RequestMapping("/sousCriterAlternativeFahp")
public class AlternativeSousCriterFahpController {

    @Autowired
    private AlternativeSousCriterFahpRepository sousCriterAlternativeFahpRepository;

    // Endpoint pour récupérer toutes les relations
    @GetMapping("/all")
    public List<SousCriterAlternativeFahp> findAll() {
        return sousCriterAlternativeFahpRepository.findAll();
    }

    // Endpoint pour enregistrer une nouvelle relation
    @PostMapping("/save")
    public SousCriterAlternativeFahp save(@RequestBody SousCriterAlternativeFahp sousCriterAlternativeFahp) {
    	
    	SousCriterAlternativePk id = sousCriterAlternativeFahp.getId(); // Récupérez l'identifiant
        double lowerBound = sousCriterAlternativeFahp.getFacteur().getLowerBound();
        double midlbound = sousCriterAlternativeFahp.getFacteur().getMidlbound();
        double upperBound = sousCriterAlternativeFahp.getFacteur().getUpperBound();

        // Créez une nouvelle instance en passant l'identifiant et les valeurs FuzzyNumber au constructeur
        SousCriterAlternativeFahp newSousCriterAlternativeFahp = new SousCriterAlternativeFahp(id, lowerBound, midlbound, upperBound);

        return sousCriterAlternativeFahpRepository.save(newSousCriterAlternativeFahp);}}
