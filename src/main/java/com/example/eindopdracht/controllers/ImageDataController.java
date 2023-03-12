package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dtos.ImageDataDto;
import com.example.eindopdracht.service.ImageDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/image")
public class ImageDataController {

    private final ImageDataService imageDataService;

    public ImageDataController(ImageDataService imageDataService) {
        this.imageDataService = imageDataService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> addImage(@PathVariable("id") Long id , @RequestParam(value = "image", required = false)MultipartFile file, ImageDataDto dto) throws IOException {
        String uploadImage = String.valueOf(imageDataService.uploadImage(id, file, dto));

        return ResponseEntity.ok().body(uploadImage);
    }


    @GetMapping("")
    public List<ImageDataDto> getAllImageData(){

        List<ImageDataDto> dtoOutputs = imageDataService.getAllImageData();

        return dtoOutputs;
    }

    @GetMapping("/{id}")
    public ImageDataDto getImageDataById(@PathVariable("id") Long id){

        ImageDataDto dataDtoOutput = imageDataService.getImageDataById(id);

        return dataDtoOutput;
    }

    @GetMapping("/Download/{fileName}")
    @Transactional
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        return imageDataService.singleFileDownload(fileName, request);
    }
}
