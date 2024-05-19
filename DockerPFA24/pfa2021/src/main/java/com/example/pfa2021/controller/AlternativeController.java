package com.example.pfa2021.controller;

import com.example.pfa2021.entities.Alternative;
import com.example.pfa2021.entities.Criter;
import com.example.pfa2021.repository.AlternativeRepository;
import com.example.pfa2021.repository.ProjetAhpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("alternatives")
public class AlternativeController {

    @Autowired
    private AlternativeRepository alternativeRepository;

    @GetMapping("/all")
    public List<Alternative> findAll(){return alternativeRepository.findAll(); }

    @PostMapping("/save")
    public Alternative save(@RequestBody Alternative alternative) {
        return alternativeRepository.save(alternative);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Alternative> updateAlternative(@PathVariable int id, @RequestBody Alternative alternative) {
        Optional<Alternative> existingAlternativeOptional = alternativeRepository.findById(id);
        if (existingAlternativeOptional.isPresent()) {
            Alternative existingAlternative = existingAlternativeOptional.get();
            existingAlternative.setCi(alternative.getCi());
            // Mettez à jour d'autres champs si nécessaire
            Alternative updatedAlternative = alternativeRepository.save(existingAlternative);
            return ResponseEntity.ok(updatedAlternative);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/updateRank/{id}")
    public ResponseEntity<Alternative> updateAlternativeRank(@PathVariable int id, @RequestBody Alternative alternative) {
        Optional<Alternative> existingAlternativeOptional = alternativeRepository.findById(id);
        if (existingAlternativeOptional.isPresent()) {
            Alternative existingAlternative = existingAlternativeOptional.get();
            existingAlternative.setRank(alternative.getRank());
            // Mettez à jour d'autres champs si nécessaire
            Alternative updatedAlternative = alternativeRepository.save(existingAlternative);
            return ResponseEntity.ok(updatedAlternative);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/updateRankCi/{id}")
    public ResponseEntity<Alternative> updateAlternativeRankCi(@PathVariable int id, @RequestBody Alternative alternative) {
        Optional<Alternative> existingAlternativeOptional = alternativeRepository.findById(id);
        if (existingAlternativeOptional.isPresent()) {
            Alternative existingAlternative = existingAlternativeOptional.get();
            existingAlternative.setRank(alternative.getRank());
            existingAlternative.setCi(alternative.getCi());

            // Mettez à jour d'autres champs si nécessaire
            Alternative updatedAlternative = alternativeRepository.save(existingAlternative);
            return ResponseEntity.ok(updatedAlternative);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getByName/{alternativeName}")
    public ResponseEntity<Alternative> getAlternativeByName(@PathVariable String alternativeName) {
        Alternative alternative = alternativeRepository.findByName(alternativeName);
        if (alternative != null) {
            return ResponseEntity.ok(alternative);
        } else {
            return ResponseEntity.notFound().build();}}
    @GetMapping("/getByProjectId/{projectId}")
    public ResponseEntity<List<Alternative>> getAlternativesByProjectId(@PathVariable int projectId) {
        List<Alternative> alternatives = alternativeRepository.findByProjetAhppId(projectId);
        if (!alternatives.isEmpty()) {
            return ResponseEntity.ok(alternatives);
        } else {
            return ResponseEntity.notFound().build();}}}
