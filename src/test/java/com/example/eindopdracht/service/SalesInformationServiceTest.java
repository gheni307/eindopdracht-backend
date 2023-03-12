package com.example.eindopdracht.service;

import com.example.eindopdracht.dtos.inputdtos.SalesInformationDtoInput;
import com.example.eindopdracht.dtos.outputdtos.SalesInformationDtoOutput;
import com.example.eindopdracht.exceptions.RecordNotFoundException;
import com.example.eindopdracht.models.Customer;
import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.models.SalesInformation;
import com.example.eindopdracht.repositories.GameOwnerRepository;
import com.example.eindopdracht.repositories.SalesInformationRepository;
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
class SalesInformationServiceTest {

    @Mock
    SalesInformationRepository salesInformationRepository;

    @Mock
    GameOwnerRepository gameOwnerRepository;

    @InjectMocks
    SalesInformationService salesInformationService;

    @Test
    void addSalesInformation() {

        GameOwner gameOwner = new GameOwner();
        gameOwner.setId(1L);

        Mockito.when(gameOwnerRepository.findById(gameOwner.getId())).thenReturn(Optional.of(gameOwner));

        SalesInformationDtoInput dtoInput = new SalesInformationDtoInput(true, false);


        SalesInformationDtoOutput dtoOutput = salesInformationService.addSalesInformation(dtoInput, 1L);

        assertEquals(true, dtoOutput.getHasBeenSold());
        assertEquals(false, dtoOutput.getSubscribed());
    }

    @Test
    void addSalesInformationTestRecordNotFoundException(){

        GameOwner gameOwner = new GameOwner();
        gameOwner.setId(1L);

        Mockito.when(gameOwnerRepository.findById(gameOwner.getId())).thenReturn(Optional.empty());

        SalesInformationDtoInput dtoInput = new SalesInformationDtoInput(true, false);

        assertThrows(RecordNotFoundException.class, ()->{salesInformationService.addSalesInformation(dtoInput, 1L);});
    }

    @Test
    void getAllSalesInformation() {

        GameOwner gameOwner = new GameOwner();
        gameOwner.setId(1L);
        GameOwner gameOwner1 = new GameOwner();
        gameOwner.setId(2L);

        Customer customer = new Customer();
        customer.setId(1L);
        Customer customer1 = new Customer();
        customer.setId(2L);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);

        SalesInformation info = new SalesInformation(1L, true, false, gameOwner, customers);
        SalesInformation info1 = new SalesInformation(2L, false, true, gameOwner1, customers);

        List<SalesInformation> infoList = new ArrayList<>();
        infoList.add(info);
        infoList.add(info1);

        Mockito.when(salesInformationRepository.findAll()).thenReturn(infoList);

        List<SalesInformationDtoOutput> infoDtos = salesInformationService.getAllSalesInformation();

        assertEquals(infoList.get(0).getId(), infoDtos.get(0).getId());
        assertEquals(infoList.get(0).getHasBeenSold(), infoDtos.get(0).getHasBeenSold());
        assertEquals(infoList.get(0).getSubscribed(), infoDtos.get(0).getSubscribed());
        assertEquals(infoList.get(0).getGameOwner(), infoDtos.get(0).getGameOwner());
        assertEquals(infoList.get(0).getCustomers(), infoDtos.get(0).getCustomers());
        assertEquals(infoList.get(1).getId(), infoDtos.get(1).getId());
        assertEquals(infoList.get(1).getHasBeenSold(), infoDtos.get(1).getHasBeenSold());
        assertEquals(infoList.get(1).getSubscribed(), infoDtos.get(1).getSubscribed());
        assertEquals(infoList.get(1).getGameOwner(), infoDtos.get(1).getGameOwner());
        assertEquals(infoList.get(1).getCustomers(), infoDtos.get(1).getCustomers());


    }

    @Test
    void getSalesInformationById() {

        GameOwner gameOwner = new GameOwner();
        gameOwner.setId(1L);

        Customer customer = new Customer();
        customer.setId(1L);
        Customer customer1 = new Customer();
        customer.setId(2L);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);

        SalesInformation info = new SalesInformation(1L, true, false, gameOwner, customers);

        Mockito.when(salesInformationRepository.findById(1l)).thenReturn(Optional.of(info));

        SalesInformationDtoOutput dtoOutput = salesInformationService.getSalesInformationById(1L);

        assertEquals(1L, dtoOutput.getId());
        assertEquals(true, dtoOutput.getHasBeenSold());
        assertEquals(false, dtoOutput.getSubscribed());
        assertEquals(gameOwner, dtoOutput.getGameOwner());
        assertEquals(customers, dtoOutput.getCustomers());

    }

    @Test
    void getSalesInformationByIdTestRecordNotFoundException(){

        SalesInformation info = new SalesInformation(1L, true, false);

        Mockito.when(salesInformationRepository.findById(info.getId())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, ()->{salesInformationService.getSalesInformationById(1L);});
    }

    @Test
    void updateSalesInformation() {

        SalesInformation info = new SalesInformation(1L, true, false);

        Mockito.when(salesInformationRepository.findById(info.getId())).thenReturn(Optional.of(info));

        SalesInformationDtoInput dtoInput = new SalesInformationDtoInput(false, true);

        SalesInformationDtoOutput dtoOutput = salesInformationService.updateSalesInformation(1L, dtoInput);

        assertEquals(false, dtoOutput.getHasBeenSold());
        assertEquals(true, dtoOutput.getSubscribed());

    }

    @Test
    void updateSalesInformationTestRecordNotFoundException(){

        SalesInformation info = new SalesInformation(1L, true, false);

        Mockito.when(salesInformationRepository.findById(info.getId())).thenReturn(Optional.empty());

        SalesInformationDtoInput dtoInput = new SalesInformationDtoInput(false, true);

        assertThrows(RecordNotFoundException.class, ()->{salesInformationService.updateSalesInformation(1L, dtoInput);});

    }

    @Test
    void deleteSalesInformation() {

        final Long id = 1L;

        salesInformationService.deleteSalesInformation(id);

        Mockito.verify(salesInformationRepository).deleteById(id);

    }
}