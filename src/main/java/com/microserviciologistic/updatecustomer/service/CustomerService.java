package com.microserviciologistic.updatecustomer.service;

import com.microserviciologistic.updatecustomer.model.Customer;
import com.microserviciologistic.updatecustomer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final WebSocketClientService webSocketClientService;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, WebSocketClientService webSocketClientService ,PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.webSocketClientService = webSocketClientService;
    }

    public Customer updateCustomer(UUID customerId, Customer updatedCustomer) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(customerId);
            if (existingCustomer.isPresent()) {
                Customer customer = existingCustomer.get();
                customer.setName(updatedCustomer.getName());
                customer.setLastname(updatedCustomer.getLastname());
                customer.setEmail(updatedCustomer.getEmail());
                customer.setPhone(updatedCustomer.getPhone());
                customer.setAddress(updatedCustomer.getAddress());
                if (updatedCustomer.getPassword() != null && !updatedCustomer.getPassword().isEmpty()) {
                    customer.setPassword(passwordEncoder.encode(updatedCustomer.getPassword()));
                }
                Customer editCustomer = customerRepository.save(customer);;
                webSocketClientService.sendEvent("UPDATE", editCustomer);

                return editCustomer;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + customerId + " not found");
            }
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error to connection with database: " + e.getMessage(), e);
        }
    }

}
