package com.example.eindopdracht.service;

import com.example.eindopdracht.exceptions.RecordNotFoundException;
import com.example.eindopdracht.dtos.inputdtos.SalesInformationDtoInput;
import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.models.SalesInformation;
import com.example.eindopdracht.dtos.outputdtos.SalesInformationDtoOutput;
import com.example.eindopdracht.models.User;
import com.example.eindopdracht.repositories.GameOwnerRepository;
import com.example.eindopdracht.repositories.SalesInformationRepository;
import com.example.eindopdracht.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesInformationService {

    private final SalesInformationRepository salesInformationRepository;
    private final GameOwnerRepository gameOwnerRepository;
    private final UserRepository userRepository;
    public SalesInformationService(SalesInformationRepository salesInformationRepository, GameOwnerRepository gameOwnerRepository, UserRepository userRepository) {

        this.salesInformationRepository = salesInformationRepository;
        this.gameOwnerRepository = gameOwnerRepository;
        this.userRepository = userRepository;
    }

    public SalesInformationDtoOutput addSalesInformation(SalesInformationDtoInput dtoInput, String username){
        Optional<GameOwner> gameOwner = Optional.ofNullable(gameOwnerRepository.findByUsername(username));

        if (gameOwner.isPresent()){
            SalesInformation info = transferToSalesInformation(dtoInput);
            salesInformationRepository.save(info);

            return transferToDto(info);
        }

        throw new RecordNotFoundException("game owner is not present");
    }

    public List<SalesInformationDtoOutput> getAllSalesInformation(){

        List<SalesInformation> salesList = salesInformationRepository.findAll();

        return transferSalesInformationToDtoList(salesList);
    }

    public SalesInformationDtoOutput getSalesInformationById(Long id){

        if (salesInformationRepository.findById(id).isPresent()){
            SalesInformation info = salesInformationRepository.findById(id).get();

            return transferToDto(info);

        } else {

            throw new RecordNotFoundException("no sales information found");
        }

    }

    public SalesInformationDtoOutput updateSalesInformation(Long id, SalesInformationDtoInput dtoInput){

        if (salesInformationRepository.findById(id).isPresent()){
            SalesInformation info = salesInformationRepository.findById(id).get();
            SalesInformation info1 = transferToSalesInformation(dtoInput);
            info1.setId(info.getId());

            salesInformationRepository.save(info1);

            return transferToDto(info1);
        } else {
            throw new RecordNotFoundException("no dales information found");
        }
    }

    public void deleteSalesInformation(@RequestBody Long id){

        salesInformationRepository.deleteById(id);
    }

    public List<SalesInformationDtoOutput> transferSalesInformationToDtoList(List<SalesInformation> salesInformations){

        List<SalesInformationDtoOutput> salesDtoList = new ArrayList<>();

        for (SalesInformation info : salesInformations){
            SalesInformationDtoOutput dto = transferToDto(info);

            salesDtoList.add(dto);
        }

        return  salesDtoList;
    }

    public SalesInformation transferToSalesInformation(SalesInformationDtoInput dtoInput){

        var salesInformation = new SalesInformation();

        salesInformation.setHasBeenSold(dtoInput.getHasBeenSold());
        salesInformation.setSubscribed(dtoInput.getSubscribed());

        return salesInformation;
    }

    public SalesInformationDtoOutput transferToDto(SalesInformation salesInformation){

        SalesInformationDtoOutput dtoOutput = new SalesInformationDtoOutput();

        dtoOutput.setId(salesInformation.getId());
        dtoOutput.setHasBeenSold(salesInformation.getHasBeenSold());
        dtoOutput.setSubscribed(salesInformation.getSubscribed());
        if (salesInformation.getGameOwner() != null){
            dtoOutput.setGameOwner(salesInformation.getGameOwner());
        }

        return dtoOutput;
    }

    public void assignUserToSalesInformation(Long id, String usernameOfUser){
        Optional<SalesInformation> salesInformation = salesInformationRepository.findById(id);
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(usernameOfUser));
        if (user.isPresent() && salesInformation.isPresent()){
            SalesInformation salesInformation1 = salesInformation.get();
            User user1 = user.get();
            salesInformation1.setUser(user1);
            salesInformationRepository.save(salesInformation1);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
