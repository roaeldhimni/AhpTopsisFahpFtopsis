package com.example.pfa2021.controller;

import com.example.pfa2021.entities.Criter;
import com.example.pfa2021.entities.SousCriter;
import com.example.pfa2021.repository.CriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("criters")
public class CriterController {

	@Autowired
    private JavaMailSender emailSender;
	
    @Autowired
    private CriterRepository criterRepository ;

    @GetMapping("/all")
    public List<Criter> findAll(){return criterRepository.findAll(); }

    @PostMapping("/save")
    public Criter save(@RequestBody Criter criter) {
        return criterRepository.save(criter);
    }

    @GetMapping("/souscriterof/{id}")
    public  List<SousCriter>  getsouscriters(@PathVariable String id ){

        int i = Integer.parseInt(id);
        Criter criter = criterRepository.findById(i);
        return criter.getSousCriters();
    }
    @GetMapping("/getCriterIdByName/{criterName}")
    public ResponseEntity<Integer> getCriterIdByName(@PathVariable String criterName) {
        Criter criter = criterRepository.findByName(criterName);
        if (criter != null) {
            return ResponseEntity.ok(criter.getId());
        } else {
            return ResponseEntity.notFound().build();}}
    @PutMapping("/{projetAhpId}/updateCR")
    public ResponseEntity<?> updateCRValueForProject(@PathVariable int projetAhpId, @RequestBody Map<String, Double> crPayload) {
        double cr = crPayload.get("cr");
        List<Criter> criterList = criterRepository.findByProjetAhpId(projetAhpId);
        if (criterList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        for (Criter criter : criterList) {
            criter.setCR(cr);
            criterRepository.save(criter);
        }
        return ResponseEntity.ok("CR values updated successfully for project ID: " + projetAhpId);
    }
    
    
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody Map<String, Object> emailRequest) {
        try {
            List<String> emails = (List<String>) emailRequest.get("emails");
            String subject = (String) emailRequest.get("subject");
            String message = (String) emailRequest.get("message");

            // Traitez les e-mails en tant que liste ici

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(emails.toArray(new String[0])); // Convertir la liste d'e-mails en tableau de chaînes
            mailMessage.setSubject(subject);
            mailMessage.setText(message);

            emailSender.send(mailMessage);

            return ResponseEntity.ok("E-mail envoyé avec succès !");
        } catch (MailException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());}}}