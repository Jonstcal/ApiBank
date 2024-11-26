package com.api.crud.services;

import com.api.crud.models.Customer;
import com.api.crud.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get a customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Update a customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(updatedCustomer.getName());
                    existingCustomer.setGender(updatedCustomer.getGender());
                    existingCustomer.setIdNumber(updatedCustomer.getIdNumber());
                    existingCustomer.setAddress(updatedCustomer.getAddress());
                    existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                    existingCustomer.setPassword(updatedCustomer.getPassword());
                    existingCustomer.setActive(updatedCustomer.getActive());
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }

    // Delete a customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
