package com.example.pfa2021.controller;


import com.example.pfa2021.entities.Criter;
import com.example.pfa2021.entities.SousCriter;
import com.example.pfa2021.repository.SousCriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("souscriters")
public class SousCriterController {

    @Autowired
    private  SousCriterRepository sousCriterRepository ;

    @GetMapping("/all")
    public List<SousCriter> findAll(){return sousCriterRepository.findAll(); }

    @PostMapping("/save")
    public SousCriter save(@RequestBody SousCriter sousCriter) {
        return sousCriterRepository.save(sousCriter);
    }

    @GetMapping("/getSCriterIdByName/{criterName}")
    public ResponseEntity<Integer> getCriterIdByName(@PathVariable String criterName) {
        SousCriter scriter = sousCriterRepository.findByName(criterName);
        if (scriter != null) {
            return ResponseEntity.ok(scriter.getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<SousCriter> updateSousCriter(@PathVariable("id") int id, @RequestBody SousCriter sousCriter) {
        Optional<SousCriter> existingSousCriterOptional = sousCriterRepository.findById(id);
        if(existingSousCriterOptional.isPresent()) {
            SousCriter existingSousCriter = existingSousCriterOptional.get();
            existingSousCriter.setPoids(sousCriter.getPoids());
            sousCriterRepository.save(existingSousCriter);
            return new ResponseEntity<>(existingSousCriter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}}
    @PostMapping("/saveCRForCriter/{criterId}/{crValue}")
    public ResponseEntity<String> saveCRForCriter(@PathVariable int criterId, @PathVariable double crValue) {
        List<SousCriter> sousCriterList = sousCriterRepository.findByCriterId(criterId);
        if (!sousCriterList.isEmpty()) {
            for (SousCriter sousCriter : sousCriterList) {
                sousCriter.setCr(crValue);
                sousCriterRepository.save(sousCriter);
            }
            return ResponseEntity.ok("La valeur de CR a été enregistrée avec succès pour tous les sous-critères associés au critère ID " + criterId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/updateWeight/{souscriterId}")
    public ResponseEntity<Double> updateSousCriterWeight(@PathVariable Long souscriterId, @RequestBody Double poids) {
        Optional<SousCriter> sousCriterOptional = sousCriterRepository.findById(souscriterId.intValue());

        if (sousCriterOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SousCriter souscriter = sousCriterOptional.get();

        double roundedPoids = Math.round(poids * 1000.0) / 1000.0;

        souscriter.setPoids(roundedPoids);
        sousCriterRepository.save(souscriter);

        return ResponseEntity.ok(souscriter.getPoids());
    }

    @PutMapping("/updateCr/{souscriterId}")
    public ResponseEntity<Double> updateSubCriteriaCr(@PathVariable Long souscriterId, @RequestBody Double cr) {
        Optional<SousCriter> sousCriterOptional = sousCriterRepository.findById(souscriterId.intValue());

        if (sousCriterOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SousCriter souscriter = sousCriterOptional.get();

        souscriter.setCr(cr);
        sousCriterRepository.save(souscriter);

        return ResponseEntity.ok(souscriter.getPoids());
    }

}