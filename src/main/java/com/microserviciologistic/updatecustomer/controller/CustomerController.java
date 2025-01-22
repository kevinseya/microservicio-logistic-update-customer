package com.microserviciologistic.updatecustomer.controller;

import com.microserviciologistic.updatecustomer.model.Customer;
import com.microserviciologistic.updatecustomer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customers", description = "Endpoints for managing customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update customer", description = "Endpoint to update customerr information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        try {
            System.out.println("Actualizando cliente con ID: " + id);
            Customer customer = customerService.updateCustomer(id, updatedCustomer);
            return ResponseEntity.ok(customer);
        } catch (ResponseStatusException e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error inesperado al actualizar el usuario: " + e.getMessage(), e
            );
        }
    }
}
