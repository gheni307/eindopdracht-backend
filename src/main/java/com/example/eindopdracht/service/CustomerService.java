package com.example.eindopdracht.service;

import com.example.eindopdracht.dtos.CustomerDto;
import com.example.eindopdracht.exceptions.RecordNotFoundException;
import com.example.eindopdracht.models.Customer;
import com.example.eindopdracht.models.Game;
import com.example.eindopdracht.models.SalesInformation;
import com.example.eindopdracht.models.User;
import com.example.eindopdracht.repositories.CustomerRepository;
import com.example.eindopdracht.repositories.GameRepository;
import com.example.eindopdracht.repositories.SalesInformationRepository;
import com.example.eindopdracht.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final SalesInformationRepository salesInformationRepository;
    private final GameRepository gameRepository;

    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository, SalesInformationRepository salesInformationRepository,
                           GameRepository gameRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.salesInformationRepository = salesInformationRepository;
        this.gameRepository = gameRepository;
    }

    public CustomerDto createCustomerTable(CustomerDto dto){
        Customer customer = toCustomer(dto);
        customerRepository.save(customer);

        return fromCustomer(customer);
    }

    public CustomerDto getCustomerTable(Long id){
        CustomerDto dto = new CustomerDto();
        Optional<Customer> customer  = customerRepository.findById(id);
        if (customer.isPresent()){
            dto = fromCustomer(customer.get());
        } else {
            throw new RecordNotFoundException();
        }
        return dto;
    }

    public List<CustomerDto> getCustomerTables(){
        List<CustomerDto> collection = new ArrayList<>();
        List<Customer> list  = customerRepository.findAll();
        for (Customer customer : list) {
            collection.add(fromCustomer(customer));
        }
        return collection;
    }

    public void deleteCustomerTable(Long id){
        customerRepository.deleteById(id);
    }

    public CustomerDto fromCustomer(Customer customer){

        var dto = new CustomerDto();

        dto.setId(customer.getId());
        if (customer.getSalesInformation() != null){
            dto.setSalesInformation(customer.getSalesInformation());
        }
        if (customer.getGames() != null){
            dto.setGames(customer.getGames());
        }
        if (customer.getUser() != null){
            dto.setUser(customer.getUser());
        }

        return dto;
    }

    public Customer toCustomer(CustomerDto dto){
        var customer = new Customer();

        customer.setId(dto.getId());

        return customer;
    }

    public void assignUserToCustomerTable(Long id, String username){
        Optional<Customer> customer = customerRepository.findById(id);
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));

        if (user.isPresent()){
            Customer customer1 = customer.get();
            User user1 = user.get();
            customer1.setUser(user1);
            customerRepository.save(customer1);

        } else {
            throw new RuntimeException("user not found");
        }
    }

    public void assignSalesInformationToCustomerTable(Long id, Long salesInformationId){
        Optional<Customer> customer  = customerRepository.findById(id);
        Optional<SalesInformation> salesInformation = salesInformationRepository.findById(salesInformationId);

        if (customer.isPresent() && salesInformation.isPresent()){
            Customer customer1 = customer.get();
            SalesInformation salesInformation1 = salesInformation.get();

            customer1.getSalesInformation().add(salesInformation1);

            customerRepository.save(customer1);
        } else {
            throw new RuntimeException();
        }
    }

    public void assignGameToCustomerTable(Long id, Long gameId){
        Optional<Customer> customer = customerRepository.findById(id);
        Optional<Game> game = gameRepository.findById(gameId);

        if (customer.isPresent() && game.isPresent()){
            Customer customer1 = customer.get();
            Game game1 = game.get();

            customer1.getGames().add(game1);

            customerRepository.save(customer1);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
