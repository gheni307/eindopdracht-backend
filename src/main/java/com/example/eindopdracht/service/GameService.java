package com.example.eindopdracht.service;

import com.example.eindopdracht.exceptions.RecordNotFoundException;
import com.example.eindopdracht.dtos.inputdtos.GameDtoInput;
import com.example.eindopdracht.models.Game;
import com.example.eindopdracht.dtos.outputdtos.GameDtoOutput;
import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.repositories.GameOwnerRepository;
import com.example.eindopdracht.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameOwnerRepository gameOwnerRepository;

    public GameService(GameRepository gameRepository, GameOwnerRepository gameOwnerRepository) {
        this.gameRepository = gameRepository;
        this.gameOwnerRepository = gameOwnerRepository;
    }

    public List<GameDtoOutput> getAllGames(){
        List<Game> gameList = gameRepository.findAll();
        return  transferGameListToDto(gameList);
    }

    public GameDtoOutput getGameById(Long id){

        if (gameRepository.findById(id).isPresent()){
            Game game = gameRepository.findById(id).get();

            return transferToDto(game);
        }else {
            throw new RecordNotFoundException("no game found");
        }
    }

    public GameDtoOutput addGame(GameDtoInput input, Long id){
        Optional<GameOwner> gameOwner = gameOwnerRepository.findById(id);
        if (gameOwner.isPresent()){
            Game game = transferToGame(input);
            game.setGameOwner(gameOwner.get());
            gameRepository.save(game);

            return transferToDto(game);
        } else {
            throw new RecordNotFoundException("no game owner found");
        }

    }

    public GameDtoOutput updateGame(Long id, GameDtoInput gameDtoInput){

        if (gameRepository.findById(id).isPresent()){
            Game game = gameRepository.findById(id).get();
            Game game1 = transferToGame(gameDtoInput);
            game1.setId(game.getId());
            gameRepository.save(game1);

            return transferToDto(game1);
        } else {
            throw new RecordNotFoundException("no game found");
        }
    }

    public void deleteGame(@RequestBody Long id){
            gameRepository.deleteById(id);
    }

    public List<GameDtoOutput> transferGameListToDto(List<Game> games){
        List<GameDtoOutput> dtoOutputs = new ArrayList<>();

        for (Game game : games){
            GameDtoOutput dto = transferToDto(game);
            dtoOutputs.add(dto);
        }
        return dtoOutputs;
    }

    public Game transferToGame(GameDtoInput dto){
        var game = new Game();

        game.setCompatible(dto.getCompatible());
        game.setName(dto.getName());
        game.setGenre(dto.getGenre());
        game.setPrice(dto.getPrice());

        return game;
    }

    public GameDtoOutput transferToDto(Game game){
        var dto = new GameDtoOutput();

        dto.setId(game.getId());
        dto.setName(game.getName());
        dto.setCompatible(game.getCompatible());
        dto.setGenre(game.getGenre());
        dto.setPrice(game.getPrice());
        if (game.getGameOwner() != null){
            dto.setGameOwner(game.getGameOwner());
        }
        if (game.getCustomers() != null){
            dto.setCustomerList(game.getCustomers());
        }

        return dto;
    }

}
