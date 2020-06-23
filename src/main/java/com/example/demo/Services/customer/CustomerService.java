package com.example.demo.Services.customer;

import com.example.demo.models.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<Customer> getAllCustomer(Pageable pageable);

    Customer getCustomerById(Long id);

    void saveCustomer(Customer customer);

    void deleteCustomerById(Long id);

    Page<Customer> findAll(String fullName, Pageable pageable);
}
