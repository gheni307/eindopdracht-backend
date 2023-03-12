package com.example.eindopdracht.service;

import com.example.eindopdracht.dtos.inputdtos.GameDtoInput;
import com.example.eindopdracht.dtos.outputdtos.GameDtoOutput;
import com.example.eindopdracht.exceptions.RecordNotFoundException;
import com.example.eindopdracht.models.Customer;
import com.example.eindopdracht.models.Game;
import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.repositories.GameOwnerRepository;
import com.example.eindopdracht.repositories.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    GameRepository gameRepository;

    @Mock
    GameOwnerRepository gameOwnerRepository;

    @InjectMocks
    GameService gameService;

    @BeforeEach
    void setup(){

    }

    @Test
    void getAllGames() {

        Game game = new Game(1L, "Evil 1", "ps", "action", 40.000);
        Game game1 = new Game(2L, "Evil 2", "pc", "action", 40.000);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);
        gameList.add(game1);

        Mockito.when(gameRepository.findAll()).thenReturn(gameList);

        List<GameDtoOutput> gameDtoOutputs = gameService.getAllGames();

        assertEquals(gameList.get(0).getId(), gameDtoOutputs.get(0).getId());
        assertEquals(gameList.get(0).getName(), gameDtoOutputs.get(0).getName());
        assertEquals(gameList.get(0).getCompatible(), gameDtoOutputs.get(0).getCompatible());
        assertEquals(gameList.get(0).getGenre(), gameDtoOutputs.get(0).getGenre());
        assertEquals(gameList.get(0).getPrice(), gameDtoOutputs.get(0).getPrice());
        assertEquals(gameList.get(1).getId(), gameDtoOutputs.get(1).getId());
        assertEquals(gameList.get(1).getName(), gameDtoOutputs.get(1).getName());
        assertEquals(gameList.get(1).getCompatible(), gameDtoOutputs.get(1).getCompatible());
        assertEquals(gameList.get(1).getGenre(), gameDtoOutputs.get(1).getGenre());
        assertEquals(gameList.get(1).getPrice(), gameDtoOutputs.get(1).getPrice());



    }

    @Test
    void getGameById() {

        GameOwner gameOwner = new GameOwner();
        gameOwner.setId(1L);

        Customer customer = new Customer();
        customer.setId(1L);
        Customer customer1 = new Customer();
        customer1.setId(2L);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);

        Game game = new Game(1L, "Evil 1", "ps", "action", 40.000, gameOwner, customers);

        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        GameDtoOutput dtoOutput = gameService.getGameById(1L);

        assertEquals(1L, dtoOutput.getId());
        assertEquals("Evil 1", dtoOutput.getName());
        assertEquals("ps", dtoOutput.getCompatible());
        assertEquals("action", dtoOutput.getGenre());
        assertEquals(40.000, dtoOutput.getPrice());
        assertEquals(1L, dtoOutput.getGameOwner().getId());
        assertEquals(1L, dtoOutput.getCustomerList().get(0).getId());
        assertEquals(2L, dtoOutput.getCustomerList().get(1).getId());
    }

    @Test
    void getGameByIdTestRecordNotFoundException(){
        Game game = new Game();
        game.setId(1L);

        Mockito.when(gameRepository.findById(game.getId())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, ()->{gameService.getGameById(1L);});
    }

    @Test
    void addGame() {

        GameOwner gameOwner = new GameOwner();
        gameOwner.setId(1L);

        Mockito.when(gameOwnerRepository.findById(gameOwner.getId())).thenReturn(Optional.of(gameOwner));

            GameDtoInput dtoInput = new GameDtoInput("Evil 1", "ps", "action", 40.000);

            GameDtoOutput dtoOutput = gameService.addGame(dtoInput, 1L);

            assertEquals("Evil 1", dtoOutput.getName());
            assertEquals("ps", dtoOutput.getCompatible());
            assertEquals("action", dtoOutput.getGenre());
            assertEquals(40.000, dtoOutput.getPrice());
            assertEquals(1L, dtoOutput.getGameOwner().getId());

    }

    @Test
    void addGameTestRecordNotFoundException() {

        GameOwner gameOwner = new GameOwner();
        gameOwner.setId(1L);

        Mockito.when(gameOwnerRepository.findById(gameOwner.getId())).thenReturn(Optional.empty());

        GameDtoInput dtoInput = new GameDtoInput("Evil 1", "ps", "action", 40.000);

        assertThrows(RecordNotFoundException.class, ()->{gameService.addGame(dtoInput, 1L);} );

    }

    @Test
    void updateGame() {

        Game game = new Game(1L, "Evil 1", "ps", "action", 40.000);

        Mockito.when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));

        GameDtoInput dtoInput = new GameDtoInput("Evil 2", "pc", "action", 40.000);

        GameDtoOutput dtoOutput = gameService.updateGame(1L, dtoInput);

        assertEquals("Evil 2", dtoOutput.getName());
        assertEquals("pc", dtoOutput.getCompatible());
        assertEquals("action", dtoOutput.getGenre());
        assertEquals(40.000, dtoOutput.getPrice());

    }

    @Test
    void updateGameTestRecordNotFoundException(){

        Game game = new Game(1L, "Evil 1", "ps", "action", 40.000);

        Mockito.when(gameRepository.findById(game.getId())).thenReturn(Optional.empty());

        GameDtoInput dtoInput = new GameDtoInput("Evil 2", "pc", "action", 40.000);

        assertThrows(RecordNotFoundException.class, ()->{gameService.updateGame(1L, dtoInput);});
    }

    @Test
    void deleteGame() {

        final Long id = 22L;

        gameService.deleteGame(id);

        Mockito.verify(gameRepository).deleteById(id);

    }
}