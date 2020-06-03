package com.example.demo.Services;

import com.example.demo.models.TypeCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TypeCustomerService {
    Page<TypeCustomer> getAllTypeCustomer(Pageable pageable);


    TypeCustomer findTypeCustomerById(Long id);

    void saveTypeCustomer(TypeCustomer typeCustomer);

    void deleteTypeCustomerById(Long id);

}
