package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dtos.inputdtos.SalesInformationDtoInput;
import com.example.eindopdracht.dtos.outputdtos.SalesInformationDtoOutput;
import com.example.eindopdracht.service.SalesInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/salesInformation")
public class SalesInformationController {

    private final SalesInformationService salesInformationService;

    public SalesInformationController(SalesInformationService salesInformationService) {
        this.salesInformationService = salesInformationService;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> addSalesInformation(@Valid @RequestBody SalesInformationDtoInput dtoInput, @PathVariable Long id){

        SalesInformationDtoOutput dtoOutput = salesInformationService.addSalesInformation(dtoInput, id);

        return ResponseEntity.created(null).body(dtoOutput);
    }

    @GetMapping("")
    public ResponseEntity<List<SalesInformationDtoOutput>> getAllSalesInformation(){

        List<SalesInformationDtoOutput> dtoOutputs = salesInformationService.getAllSalesInformation();

        return ResponseEntity.ok().body(dtoOutputs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesInformationDtoOutput> getSalesInformationById(@PathVariable Long id){

        SalesInformationDtoOutput salesInfo = salesInformationService.getSalesInformationById(id);

        return ResponseEntity.ok().body(salesInfo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSalesInformation(@PathVariable Long id, @Valid @RequestBody SalesInformationDtoInput dtoInput){

        SalesInformationDtoOutput dtoOutput = salesInformationService.updateSalesInformation(id, dtoInput);

        return ResponseEntity.ok().body(dtoOutput);
    }
    @PostMapping("/{id}/customer/{id}")
    public void  assignCustomerToSalesInformation(@PathVariable("id") Long id, @PathVariable("id") Long customerId){
        salesInformationService.assignCustomerToSalesInformation(id, customerId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSalesInformation(@PathVariable Long id){

        salesInformationService.deleteSalesInformation(id);

        return ResponseEntity.noContent().build();
    }

}
