package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dtos.inputdtos.GameDtoInput;
import com.example.eindopdracht.dtos.outputdtos.GameDtoOutput;
import com.example.eindopdracht.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> addGame(@Valid @RequestBody GameDtoInput gameDtoInput, @PathVariable("id") Long id){
        GameDtoOutput dtoOutput = gameService.addGame(gameDtoInput, id);

        return ResponseEntity.created(null).body(dtoOutput);
    }

    @GetMapping("")
    @Transactional
    public ResponseEntity<List<GameDtoOutput>> getAllGames(){
        List<GameDtoOutput> dtoOutputs = gameService.getAllGames();

        return ResponseEntity.ok().body(dtoOutputs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDtoOutput> getGameById(@PathVariable("id") Long id){
        GameDtoOutput game = gameService.getGameById(id);

        return ResponseEntity.ok().body(game);
    }



    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> updateGame(@PathVariable("id") Long id, @RequestBody GameDtoInput gameDtoInput){
        GameDtoOutput dto = gameService.updateGame(id, gameDtoInput);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteGame(@PathVariable Long id){
        gameService.deleteGame(id);

        return ResponseEntity.noContent().build();
    }


}
