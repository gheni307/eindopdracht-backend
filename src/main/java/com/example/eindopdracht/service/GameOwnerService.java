package com.example.eindopdracht.service;

import com.example.eindopdracht.exceptions.RecordNotFoundException;
import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.dtos.GameOwnerDto;
import com.example.eindopdracht.models.ImageData;
import com.example.eindopdracht.models.SalesInformation;
import com.example.eindopdracht.models.User;
import com.example.eindopdracht.repositories.GameOwnerRepository;
import com.example.eindopdracht.repositories.ImageRepository;
import com.example.eindopdracht.repositories.SalesInformationRepository;
import com.example.eindopdracht.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameOwnerService {

    private final GameOwnerRepository gameOwnerRepository;
    private final ImageRepository imageRepository;
    private final SalesInformationRepository salesInformationRepository;

    private final UserRepository userRepository;

    public GameOwnerService(GameOwnerRepository gameOwnerRepository, ImageRepository imageRepository, SalesInformationRepository salesInformationRepository, UserRepository userRepository) {
        this.gameOwnerRepository = gameOwnerRepository;
        this.imageRepository = imageRepository;
        this.salesInformationRepository = salesInformationRepository;
        this.userRepository = userRepository;
    }

    public List<GameOwnerDto> getGameOwners() {
        List<GameOwnerDto> collection = new ArrayList<>();
        List<GameOwner> list = gameOwnerRepository.findAll();
        for (GameOwner gameOwner : list) {
            collection.add(fromGameOwner(gameOwner));
        }
        return collection;
    }

    public GameOwnerDto getOwner(Long id) {
        GameOwnerDto dto = new GameOwnerDto();
        Optional<GameOwner> gameOwner = gameOwnerRepository.findById(id);
        if (gameOwner.isPresent()){
            dto = fromGameOwner(gameOwner.get());
        }else {
            throw new RecordNotFoundException();
        }
        return dto;
    }



    public GameOwnerDto createGameOwner(GameOwnerDto gameOwnerDto) {
        GameOwner gameOwner = toGameOwner(gameOwnerDto);
        gameOwnerRepository.save(gameOwner);

        return fromGameOwner(gameOwner);
    }


    public void deleteGameOwner(Long id) {
        gameOwnerRepository.deleteById(id);
    }

    public static GameOwnerDto fromGameOwner(GameOwner gameOwner){

        var dto = new GameOwnerDto();

        dto.setId(gameOwner.getId());
        if (gameOwner.getUser() != null){
            dto.setUser(gameOwner.getUser());
        }
        if (gameOwner.getSalesInformation() != null){
            dto.setSalesInformation(gameOwner.getSalesInformation());
        }

        return dto;
    }

    public GameOwner toGameOwner(GameOwnerDto gameOwnerDto) {

        var gameOwner = new GameOwner();

        gameOwner.setId(gameOwnerDto.getId());

        return gameOwner;
    }

    public void assignImageToGameOwner(Long id, String nameOfImage){
        Optional<GameOwner> gameOwner = gameOwnerRepository.findById(id);
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

    public void assignSalesInformationToGameOwner(Long id, Long salesInformationId){
        Optional<GameOwner> gameOwner = gameOwnerRepository.findById(id);
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

    public void assignUserToGameOwner(Long id,String username){
        Optional<GameOwner> gameOwner = gameOwnerRepository.findById(id);
        Optional<User> nameOfUser = Optional.ofNullable(userRepository.findByUsername(username));

        if (nameOfUser.isPresent()){
                GameOwner gameOwner1 = gameOwner.get();
                User user = nameOfUser.get();
                gameOwner1.setUser(user);
                gameOwnerRepository.save(gameOwner1);

        } else {
            throw new RecordNotFoundException();
        }



    }

}
