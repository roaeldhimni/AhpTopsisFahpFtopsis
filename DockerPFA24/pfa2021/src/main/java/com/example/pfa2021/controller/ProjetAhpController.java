package com.example.pfa2021.controller;

import com.example.pfa2021.entities.Criter;
import com.example.pfa2021.entities.ProjetAhp;
import com.example.pfa2021.entities.User;
import com.example.pfa2021.repository.ProjetAhpRepository;
import com.example.pfa2021.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projets")
public class ProjetAhpController {
    @Autowired
    private ProjetAhpRepository projetahpRepository ;

    @GetMapping("/all")
    public List<ProjetAhp> findAll(){return projetahpRepository.findAll(); }

    @PostMapping("/save")
    public ProjetAhp save(@RequestBody ProjetAhp user){

        return projetahpRepository.save(user);
    }

    @GetMapping("/criterof/{id}")
    public  List<Criter>  test1(@PathVariable String id ){

        int i = Integer.parseInt(id);

System.out.println(projetahpRepository.getcriters(i));
        return projetahpRepository.getcriters(i);

    }

    @GetMapping("/latestProjectId")
    public ResponseEntity<Long> getLatestProjectId() {
        Long latestProjectId = projetahpRepository.getLatestProjectId();
        return ResponseEntity.ok(latestProjectId);
    }


}
