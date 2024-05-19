package com.example.pfa2021.controller;

import com.example.pfa2021.entities.Alternative;

import com.example.pfa2021.entities.Criter;
import com.example.pfa2021.entities.Expert;
import com.example.pfa2021.entities.ProjetAhp;
import com.example.pfa2021.entities.SousCriter;
import com.example.pfa2021.entities.User;
import com.example.pfa2021.repository.AlternativeRepository;
import com.example.pfa2021.repository.CriterRepository;
import com.example.pfa2021.repository.ExpertRepository;
import com.example.pfa2021.repository.SousCriterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;



@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("experts")
public class ExpertController {

	
	
	
    @Autowired
    private ExpertRepository expertRepository ;
    @Autowired
    private SousCriterRepository sousCriterRepository ;
    @Autowired
    private AlternativeRepository alternativeRepository ;
    @Autowired
    private CriterRepository criterRepository ;

    @GetMapping("/all")
    public List<Expert> findAll(){
        return expertRepository.findAll();
    }

    @GetMapping("/emails")
    public List<String> findAllEmails(){
        List<Expert> experts = expertRepository.findAll();
        List<String> emails = new ArrayList<>();
        for (Expert expert : experts) {
            emails.add(expert.getEmail()); // Suppose que getEmail() retourne l'email de l'expert
        }
        return emails;
    }
    @GetMapping("/login/{username}/{password}")
    public boolean login(@PathVariable String username , @PathVariable String password ){
        boolean a = false ;
        List<Expert> expertALL= this.findAll();
        for (Expert u : expertALL){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                a = true ;
            }
        }
        return a ;
    }
   
    @GetMapping("/loginExpert/{username}/{password}")
    public Expert loginExpert(@PathVariable String username , @PathVariable String password ){
       Expert exp = null ;
        List<Expert> userALL= this.findAll();
        for (Expert u : userALL){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
               exp = u ;}}return exp;}

    
    @PostMapping("/save")
    public void save(@RequestBody Expert expert){
        expertRepository.save(expert);}
    @GetMapping("/findByEmail/{email}")
    public Expert findByEmail(@PathVariable String email) {
        return expertRepository.findByEmail(email);
    }
    
    @PutMapping("/updateProjectId/{id}")
    public ResponseEntity<?> updateProjectId(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
        Long projectId = requestBody.get("projectId");
        Expert expert = expertRepository.findById(id).orElse(null);
        if (expert != null) {
            expert.setProjetId(projectId);
            expertRepository.save(expert);
            return ResponseEntity.ok().body("{\"message\": \"Expert updated successfully\"}");
        } else {
            return ResponseEntity.notFound().build();}}
    
   
    @GetMapping("/projectDetails/{projectId}")
    public ResponseEntity<?> getProjectDetails(@PathVariable int projectId) {
        // Récupérer les critères associés au projet
        List<Criter> criteres = criterRepository.findByProjetAhpId(projectId);

        // Liste pour stocker tous les sous-critères associés à tous les critères
        List<SousCriter> sousCriters = new ArrayList<>();

        // Pour chaque critère, récupérer les sous-critères associés
        for (Criter criter : criteres) {
            sousCriters.addAll(criter.getSousCriters());
        }

        // Récupérer les alternatives associées au projet
        List<Alternative> alternatives = alternativeRepository.findByProjetAhppId(projectId);

        Map<String, Object> projectDetails = new HashMap<>();
        projectDetails.put("criteres", criteres);
        projectDetails.put("sousCriters", sousCriters);
        projectDetails.put("alternatives", alternatives);

        return ResponseEntity.ok(projectDetails);
    }

    
    @GetMapping("/expert/username/{email}")
    public ResponseEntity<String> getExpertUsernameByEmail(@PathVariable String email) {
        try {
            Expert expert = expertRepository.findByEmail(email);
            if (expert != null) {
                return ResponseEntity.ok(expert.getUsername());
            } else {
                throw new EntityNotFoundException("Expert not found for email: " + email);
            }
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred",ex);}}
}