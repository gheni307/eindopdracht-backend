package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface ImageRepository extends JpaRepository<ImageData, Long> {
    ImageData findByNameOfImage(String fileName);
}
