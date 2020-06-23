package com.example.demo.Repository.customer;

import com.example.demo.models.customer.TypeCustomer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCustomerRepository extends PagingAndSortingRepository<TypeCustomer, Long> {

}
