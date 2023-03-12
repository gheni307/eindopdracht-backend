package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dtos.CustomerDto;
import com.example.eindopdracht.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "")
    public ResponseEntity<CustomerDto> createCustomerTable(@RequestBody CustomerDto dto){
        CustomerDto customerDto = customerService.createCustomerTable(dto);

        return ResponseEntity.created(null).body(customerDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> getCustomerTable(@PathVariable("id") Long id){
        CustomerDto dto = customerService.getCustomerTable(id);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<CustomerDto>> getCustomerTables(){
        List<CustomerDto> customerDtos = customerService.getCustomerTables();

        return ResponseEntity.ok().body(customerDtos);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCustomerTable(@PathVariable("id") Long id){
        customerService.deleteCustomerTable(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/customer/{username}")
    public void assignUserToCustomerTable(@PathVariable("id") Long id, @PathVariable("username") String username){
        customerService.assignUserToCustomerTable(id, username);

    }

    @PutMapping("/{id}/salesinfo/{id}")
    public void assignSalesInformationToCustomerTable(@PathVariable("id") Long id, @PathVariable("id") Long salesInformationId){
        customerService.assignSalesInformationToCustomerTable(id, salesInformationId);

    }

    @PutMapping("{id}/game/{id}")
    public void assignGameToCustomerTable(@PathVariable("id") Long id, @PathVariable("id") Long gameId){
        customerService.assignGameToCustomerTable(id, gameId);

    }
}
