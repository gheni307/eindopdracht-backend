package com.example.eindopdracht.service;

import com.example.eindopdracht.exceptions.RecordNotFoundException;
import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.models.ImageData;
import com.example.eindopdracht.dtos.ImageDataDto;
import com.example.eindopdracht.repositories.GameOwnerRepository;
import com.example.eindopdracht.repositories.ImageRepository;
import com.example.eindopdracht.utils.ImageUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageDataService {

    private final ImageRepository imageRepository;
    private final GameOwnerRepository gameOwnerRepository;

    public ImageDataService(ImageRepository imageRepository, GameOwnerRepository gameOwnerRepository) {
        this.imageRepository = imageRepository;
        this.gameOwnerRepository = gameOwnerRepository;
    }

    public ResponseEntity<String> uploadImage(String userName ,MultipartFile file, ImageDataDto dto) throws IOException {
        Optional<GameOwner> gameOwner = Optional.ofNullable(gameOwnerRepository.findByUsername(userName));
        if (gameOwner.isPresent()){
            ImageData imageData = transferToImage(dto);
            imageRepository.save(imageData.builder()
                    .nameOfImage(file.getOriginalFilename())
                    .typeOfImage(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes())).build());
            if (imageData != null){
                return ResponseEntity.ok().body(file.getOriginalFilename());
            }
        }

        return ResponseEntity.badRequest().build();
    }

    public ImageDataDto getImageDataById(Long id){
        Optional<ImageData> imageData = imageRepository.findById(id);
        if (imageData.isPresent()){
            return transferToDto(imageData.get());
        } else {
            throw new RecordNotFoundException("no image found");
        }
    }

    public List<ImageDataDto> getAllImageData(){
        List<ImageDataDto> dtoOutputs = new ArrayList<>();
        List<ImageData> imageData = imageRepository.findAll();
        for (ImageData imageData1 : imageData){
            dtoOutputs.add(transferToDto(imageData1));
        }
        return dtoOutputs;
    }

    public void deleteImage(Long id){
        imageRepository.deleteById(id);
    }

    public ImageData transferToImage(ImageDataDto dto){

        var image = new ImageData();

        image.setNameOfImage(dto.getNameOfImage());
        image.setTypeOfImage(dto.getTypeOfImage());
        image.setImageData(dto.getImageData());

        return image;

    }

    public ImageDataDto transferToDto(ImageData imageData){
        var dto = new ImageDataDto();

        dto.setId(imageData.getId());
        dto.setNameOfImage(imageData.getNameOfImage());
        dto.setTypeOfImage(imageData.getTypeOfImage());
        dto.setImageData(imageData.getImageData());

        return dto;
    }

    public ResponseEntity<byte[]> singleFileDownload(String fileName, HttpServletRequest request){

        ImageData document = imageRepository.findByNameOfImage(fileName);

        String mimeType = request.getServletContext().getMimeType(document.getNameOfImage());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" +
                document.getNameOfImage()).body(document.getImageData());

    }
}
