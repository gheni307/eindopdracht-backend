package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dtos.outputdtos.SalesInformationDtoOutput;
import com.example.eindopdracht.models.SalesInformation;
import com.example.eindopdracht.repositories.SalesInformationRepository;
import com.example.eindopdracht.service.SalesInformationService;
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
class SalesInformationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SalesInformationRepository salesInformationRepository;

    @Autowired
    SalesInformationService salesInformationService;

    SalesInformationDtoOutput dtoOutput;
    SalesInformation salesInformation;

    @BeforeEach
    void setUp(){

        salesInformation = new SalesInformation(1L, true, false);
        salesInformationRepository.save(salesInformation);

        dtoOutput = new SalesInformationDtoOutput();
        dtoOutput.setId(salesInformation.getId());
        dtoOutput.setHasBeenSold(salesInformation.getHasBeenSold());
        dtoOutput.setSubscribed(salesInformation.getSubscribed());
    }

    @Test
    void getSalesInformationById() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/salesInformation/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasBeenSold", is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subscribed", is(false)));

    }
}