package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.GameOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface GameOwnerRepository extends JpaRepository<GameOwner, Long> {

}
