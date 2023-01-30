package com.example.eindopdracht.dtos;

import com.example.eindopdracht.models.GameOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageDataDto {

    private Long id;

    private String nameOfImage;

    private String typeOfImage;

    private byte[] imageData;
}
