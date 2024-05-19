package com.example.pfa2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pfa2021.entities.Alternative;
import com.example.pfa2021.entities.ExpertSousCriterAlternative;
import com.example.pfa2021.entities.SousCriter;
import com.example.pfa2021.entities.ExpertSousCriterAlternative;
import com.example.pfa2021.entities.SousCriterAlternativePk;
import com.example.pfa2021.repository.AlternativeRepository;
import com.example.pfa2021.repository.AlternativeSousCriterFahpRepository;
import com.example.pfa2021.repository.ExpertSousCriterAlternativeRepository;
import com.example.pfa2021.repository.SousCriterRepository;

import javassist.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expertSousCriterAlternative")
public class ExpertSousCriterAlternativeController {

    @Autowired
    private ExpertSousCriterAlternativeRepository expertSousCriterAlternativeRepository;
    @Autowired
    private SousCriterRepository sousCriterRepository;
    @Autowired
    private AlternativeRepository alternativeRepository;

    // Endpoint pour récupérer toutes les relations
    @GetMapping("/all")
    public List<ExpertSousCriterAlternative> findAll() {
        return expertSousCriterAlternativeRepository.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ExpertSousCriterAlternative expertSousCriterAlternative) {
        try {
            int id = expertSousCriterAlternative.getId();
            
            // Récupérer les instances de SousCriter et Alternative à partir de leurs identifiants
            int sousCriterId = expertSousCriterAlternative.getSousCriter().getId();
            SousCriter sousCriter = sousCriterRepository.findById(sousCriterId)
                .orElseThrow(() -> new NotFoundException("SousCriter introuvable avec l'identifiant: " + sousCriterId));

            int alternativeId = expertSousCriterAlternative.getAlternative().getId();
            Alternative alternative = alternativeRepository.findById(alternativeId)
                .orElseThrow(() -> new NotFoundException("Alternative introuvable avec l'identifiant: " + alternativeId));

            // Obtenez les valeurs de lowerBound, midlbound et upperBound à partir de l'objet facteur
            double lowerBound = expertSousCriterAlternative.getFacteur().getLowerBound();
            double midlbound = expertSousCriterAlternative.getFacteur().getMidlbound();
            double upperBound = expertSousCriterAlternative.getFacteur().getUpperBound();

            // Créez une nouvelle instance en passant l'identifiant et les valeurs FuzzyNumber au constructeur
            ExpertSousCriterAlternative newExpertSousCriterAlternative = new ExpertSousCriterAlternative(id, lowerBound, midlbound, upperBound);
            
            // Mettre à jour les références de sousCriter et alternative
            newExpertSousCriterAlternative.setSousCriter(sousCriter);
            newExpertSousCriterAlternative.setAlternative(alternative);
            
            // Enregistrez dans la base de données
            ExpertSousCriterAlternative savedExpertSousCriterAlternative = expertSousCriterAlternativeRepository.save(newExpertSousCriterAlternative);

            // Retournez une réponse avec l'objet sauvegardé
            return ResponseEntity.ok(savedExpertSousCriterAlternative);
        } catch (NotFoundException e) {
            // Gérer les cas où le SousCriter ou l'Alternative n'ont pas été trouvés
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Gérer les autres exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
   


}
