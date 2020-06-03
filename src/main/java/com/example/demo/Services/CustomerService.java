package com.example.demo.Services;

import com.example.demo.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface CustomerService {

    Page<Customer> getAllCustomer(Pageable pageable);

    Customer getCustomerById(Long id);

    void saveCustomer(Customer customer);

    void deleteCustomerById(Long id);
}
