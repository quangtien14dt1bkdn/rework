package com.example.demo.Repository.contract;

import com.example.demo.models.contractWar.AccompaniedService;
import com.example.demo.models.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccompaniedServiceRepository extends PagingAndSortingRepository<AccompaniedService,Long> {
    Page<AccompaniedService> findByNameContaining(String fullName, Pageable pageable);
}
