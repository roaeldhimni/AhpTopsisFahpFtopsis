package com.example.pfa2021.repository;

import com.example.pfa2021.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findById(int i );
}
