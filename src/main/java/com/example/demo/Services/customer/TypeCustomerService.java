package com.example.demo.Services.customer;

import com.example.demo.models.customer.TypeCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TypeCustomerService {
    Page<TypeCustomer> getAllTypeCustomer(Pageable pageable);


    TypeCustomer findTypeCustomerById(Long id);

    void saveTypeCustomer(TypeCustomer typeCustomer);

    void deleteTypeCustomerById(Long id);

}
