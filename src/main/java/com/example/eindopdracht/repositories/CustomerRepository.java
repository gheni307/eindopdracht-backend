package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
