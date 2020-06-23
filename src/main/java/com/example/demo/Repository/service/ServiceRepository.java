package com.example.demo.Repository.service;

import com.example.demo.models.employee.Employee;
import com.example.demo.models.service.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ServiceRepository extends PagingAndSortingRepository<service, Long> {
    Page<service> findByNameContaining(String fullName, Pageable pageable);
}
