package com.example.pfa2021.controller;

import com.example.pfa2021.entities.Expert;
import com.example.pfa2021.entities.ProjetAhp;
import com.example.pfa2021.entities.User;
import com.example.pfa2021.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository ;

    @GetMapping("/all")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody User user){
        userRepository.save(user);
    }

    @PostMapping("/saveUser")
    public boolean saveUser(@RequestBody User user){
        boolean a = false ;
        if(userRepository.save(user).equals(user)){
            a = true ;
        }
        return a ;
    }
   




    @GetMapping("/login/{username}/{password}")
    public boolean login(@PathVariable String username , @PathVariable String password ){
        boolean a = false ;
        List<User> userALL= this.findAll();
        for (User u : userALL){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                a = true ;
            }
        }
        return a ;
    }

    @GetMapping("/loginUser/{username}/{password}")
    public User loginUser(@PathVariable String username , @PathVariable String password ){
       User user = null ;
        List<User> userALL= this.findAll();
        for (User u : userALL){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
               user = u ;
            }
        }
        return user ;
    }

    @GetMapping("/getProjets/{id}")
    public List<ProjetAhp> getProjects(@PathVariable String id ){
        int i = Integer.parseInt(id);
        User u = userRepository.findById(i);

        return u.getProjetAhps() ;
    }
}
