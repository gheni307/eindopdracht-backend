package com.example.eindopdracht.repositories;


import com.example.eindopdracht.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String userName);
}
