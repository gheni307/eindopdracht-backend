package com.example.eindopdracht.repositories;


import com.example.eindopdracht.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String userName);
    Optional<User> findByAuthorities(String authorities);
}
