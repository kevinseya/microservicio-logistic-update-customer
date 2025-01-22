package com.microserviciologistic.updatecustomer.repository;
import com.microserviciologistic.updatecustomer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}