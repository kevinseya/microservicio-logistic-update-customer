package com.microserviciologistic.updatecustomer.service;

import com.microserviciologistic.updatecustomer.model.Customer;
import com.microserviciologistic.updatecustomer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(customerId);
            if (existingCustomer.isPresent()) {
                Customer customer = existingCustomer.get();
                customer.setName(updatedCustomer.getName());
                customer.setLastname(updatedCustomer.getLastname());
                customer.setEmail(updatedCustomer.getEmail());
                customer.setPhone(updatedCustomer.getPhone());
                customer.setAddress(updatedCustomer.getAddress());
                return customerRepository.save(customer);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente con id " + customerId + " no encontrado");
            }
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error de conexión a la base de datos: " + e.getMessage(), e);
        }
    }

}
