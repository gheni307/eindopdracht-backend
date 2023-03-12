package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dtos.outputdtos.GameDtoOutput;
import com.example.eindopdracht.models.Game;
import com.example.eindopdracht.repositories.GameRepository;
import com.example.eindopdracht.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GameService gameService;

    @Autowired
    GameRepository gameRepository;

    Game game;
    GameDtoOutput dtoOutput;

    @BeforeEach
    void setUp(){

        game = new Game(1L, "Evil 1", "ps", "action", 40.00);
        gameRepository.save(game);

        dtoOutput = new GameDtoOutput();
        dtoOutput.setId(game.getId());
        dtoOutput.setName(game.getName());
        dtoOutput.setCompatible(game.getCompatible());
        dtoOutput.setGenre(game.getGenre());
        dtoOutput.setPrice(game.getPrice());

    }

    @Test
    void getGameById() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/games/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Evil 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.compatible", is("ps")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre", is("action")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(40.00)));
    }

}