package com.example.eindopdracht.controllers;

import com.example.eindopdracht.exceptions.BadRequestException;
import com.example.eindopdracht.dtos.GameOwnerDto;
import com.example.eindopdracht.service.GameOwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/gameOwners")
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

    @GetMapping(value = "/{username}")
    public ResponseEntity<GameOwnerDto> getGameOwner(@PathVariable("username") String username) {

        GameOwnerDto optionalUser = gameOwnerService.getOwner(username);


        return ResponseEntity.ok().body(optionalUser);

    }

    @PostMapping(value = "")
    @Transactional
    public ResponseEntity<GameOwnerDto> createGameOwner(@RequestBody GameOwnerDto dto){;

        String newUsername = gameOwnerService.createGameOwner(dto);
        gameOwnerService.addAuthority(newUsername, "ROLE_GAME_OWNER");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{username}")
    @Transactional
    public ResponseEntity<GameOwnerDto> updateGameOwner(@PathVariable("username") String username, @RequestBody GameOwnerDto gameOwnerDto) {

        gameOwnerService.updateGameOwner(username, gameOwnerDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/{nameOfImage}")
    @Transactional
    public void assignImageToGameOwner(@PathVariable("username") String username, @PathVariable("nameOfImage") String nameOfImage){
        gameOwnerService.assignImageToGameOwner(username, nameOfImage);
    }

    @PutMapping("/{username}/salesInfo/{id}")
    @Transactional
    public void assignSalesInformationToGameOwner(@PathVariable("username") String username, @PathVariable("id") Long id){
        gameOwnerService.assignSalesInformationToGameOwner(username,id);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteGameOwner(@PathVariable("username") String username) {
        gameOwnerService.deleteGameOwner(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getGameOwnerAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(gameOwnerService.getAuthorities(username));
    }

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addGameOwnerAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            gameOwnerService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        gameOwnerService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }
}
