package com.example.eindopdracht.service;

import com.example.eindopdracht.exceptions.RecordNotFoundException;
import com.example.eindopdracht.exceptions.UsernameNotFoundException;
import com.example.eindopdracht.models.Authority;
import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.dtos.GameOwnerDto;
import com.example.eindopdracht.models.ImageData;
import com.example.eindopdracht.models.SalesInformation;
import com.example.eindopdracht.repositories.GameOwnerRepository;
import com.example.eindopdracht.repositories.ImageRepository;
import com.example.eindopdracht.repositories.SalesInformationRepository;
import com.example.eindopdracht.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GameOwnerService {

    private final GameOwnerRepository gameOwnerRepository;
    private final ImageRepository imageRepository;
    private final SalesInformationRepository salesInformationRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public GameOwnerService(GameOwnerRepository gameOwnerRepository, ImageRepository imageRepository, SalesInformationRepository salesInformationRepository) {
        this.gameOwnerRepository = gameOwnerRepository;
        this.imageRepository = imageRepository;
        this.salesInformationRepository = salesInformationRepository;
    }

    public List<GameOwnerDto> getGameOwners() {
        List<GameOwnerDto> collection = new ArrayList<>();
        List<GameOwner> list = gameOwnerRepository.findAll();
        for (GameOwner gameOwner : list) {
            collection.add(fromGameOwner(gameOwner));
        }
        return collection;
    }

    public GameOwnerDto getOwner(String username) {
        GameOwnerDto dto = new GameOwnerDto();
        Optional<GameOwner> gameOwner = gameOwnerRepository.findById(username);
        if (gameOwner.isPresent()){
            dto = fromGameOwner(gameOwner.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean gameOwnerExists(String username) {
        return gameOwnerRepository.existsById(username);
    }

    public String createGameOwner(GameOwnerDto gameOwnerDto) {

        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        gameOwnerDto.setPassword(passwordEncoder.encode(gameOwnerDto.getPassword()));
        gameOwnerDto.setApikey(randomString);
        GameOwner gameOwner = gameOwnerRepository.save(toGameOwner(gameOwnerDto));
        return gameOwner.getUsername();
    }


    public void deleteGameOwner(String username) {
        gameOwnerRepository.deleteById(username);
    }

    public void updateGameOwner(String username, GameOwnerDto gameOwnerDto) {

        if (!gameOwnerRepository.existsById(username)) throw new UsernameNotFoundException(username);
        GameOwner gameOwner = gameOwnerRepository.findById(username).get();
        gameOwner.setPassword(gameOwnerDto.getPassword());
        gameOwnerRepository.save(gameOwner);
    }



    public Set<Authority> getAuthorities(String username) {

        if (!gameOwnerRepository.existsById(username)) throw new UsernameNotFoundException(username);
        GameOwner gameOwner = gameOwnerRepository.findById(username).get();
        GameOwnerDto gameOwnerDto = fromGameOwner(gameOwner);
        return gameOwnerDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!gameOwnerRepository.existsById(username)) throw new UsernameNotFoundException(username);
        GameOwner gameOwner = gameOwnerRepository.findById(username).get();
        gameOwner.addAuthority(new Authority(username, authority));
        gameOwnerRepository.save(gameOwner);
    }

    public void removeAuthority(String username, String authority) {

        if (!gameOwnerRepository.existsById(username)) throw new UsernameNotFoundException(username);
        GameOwner gameOwner = gameOwnerRepository.findById(username).get();
        Authority authorityToRemove = gameOwner.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        gameOwner.removeAuthority(authorityToRemove);
        gameOwnerRepository.save(gameOwner);
    }

    public static GameOwnerDto fromGameOwner(GameOwner gameOwner){

        var dto = new GameOwnerDto();

        dto.username = gameOwner.getUsername();
        dto.password = gameOwner.getPassword();
        dto.enabled = gameOwner.isEnabled();
        dto.apikey = gameOwner.getApikey();
        dto.email = gameOwner.getEmail();
        if (gameOwner.getImageData() != null)
            dto.setImageData(gameOwner.getImageData().getImageData());
        if (gameOwner.getSalesInformation() != null){
            dto.setSalesInformation(gameOwner.getSalesInformation());
        }
        dto.authorities = gameOwner.getAuthorities();

        return dto;
    }

    public GameOwner toGameOwner(GameOwnerDto gameOwnerDto) {

        var gameOwner = new GameOwner();

        gameOwner.setUsername(gameOwnerDto.getUsername());
        gameOwner.setPassword(gameOwnerDto.getPassword());
        gameOwner.setEnabled(gameOwnerDto.getEnabled());
        gameOwner.setApikey(gameOwnerDto.getApikey());
        gameOwner.setEmail(gameOwnerDto.getEmail());

        return gameOwner;
    }

    public void assignImageToGameOwner(String username, String nameOfImage){
        Optional<GameOwner> gameOwner = Optional.ofNullable(gameOwnerRepository.findByUsername(username));
        Optional<ImageData> imageData = Optional.ofNullable(imageRepository.findByNameOfImage(nameOfImage));

        if (gameOwner.isPresent() && imageData.isPresent()){
            GameOwner gameOwner1 = gameOwner.get();
            ImageData imageData1 = imageData.get();

            gameOwner1.setImageData(imageData1);
            gameOwnerRepository.save(gameOwner1);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void assignSalesInformationToGameOwner(String username, Long salesInformationId){
        Optional<GameOwner> gameOwner = Optional.ofNullable(gameOwnerRepository.findByUsername(username));
        Optional<SalesInformation> salesInfo = salesInformationRepository.findById(salesInformationId);

        if (gameOwner.isPresent() && salesInfo.isPresent()){
            GameOwner gameOwner1 = gameOwner.get();
            SalesInformation salesInformation = salesInfo.get();
            gameOwner1.setSalesInformation(salesInformation);
            gameOwnerRepository.save(gameOwner1);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
