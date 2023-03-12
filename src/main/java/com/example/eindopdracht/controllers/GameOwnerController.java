package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dtos.GameOwnerDto;
import com.example.eindopdracht.service.GameOwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/gameowners")
public class GameOwnerController {

    private final GameOwnerService gameOwnerService;

    public GameOwnerController(GameOwnerService gameOwnerService) {
        this.gameOwnerService = gameOwnerService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<GameOwnerDto>> getGameOwners() {

        List<GameOwnerDto> gameOwnerDtos = gameOwnerService.getGameOwners();

        return ResponseEntity.ok().body(gameOwnerDtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GameOwnerDto> getGameOwner(@PathVariable("id") Long id) {

        GameOwnerDto optionalUser = gameOwnerService.getOwner(id);


        return ResponseEntity.ok().body(optionalUser);

    }

    @PostMapping(value = "")
    @Transactional
    public ResponseEntity<GameOwnerDto> createGameOwner(@RequestBody GameOwnerDto dto){
        GameOwnerDto gameOwnerDto = gameOwnerService.createGameOwner(dto);

        return ResponseEntity.created(null).body(gameOwnerDto);
    }

    @PutMapping("/{id}/{nameOfImage}")
    @Transactional
    public void assignImageToGameOwner(@PathVariable("id") Long id, @PathVariable("nameOfImage") String nameOfImage){
        gameOwnerService.assignImageToGameOwner(id, nameOfImage);
    }

    @PostMapping("/{id}/salesInfo/{id}")
    @Transactional
    public void assignSalesInformationToGameOwner(@PathVariable("id") Long id, @PathVariable("id") Long salesInformationId){
        gameOwnerService.assignSalesInformationToGameOwner(id,salesInformationId);
    }

    @PutMapping("/{id}/user/{username}")
    public void assignUserToGameOwner(@PathVariable("id") Long id, @PathVariable("username") String username){
        gameOwnerService.assignUserToGameOwner(id, username);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteGameOwner(@PathVariable("id") Long id) {
        gameOwnerService.deleteGameOwner(id);
        return ResponseEntity.noContent().build();
    }

}
