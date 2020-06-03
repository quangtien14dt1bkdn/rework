package com.example.demo.Repository;

import com.example.demo.models.TypeCustomer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCustomerRepository extends PagingAndSortingRepository<TypeCustomer, Long> {

}
