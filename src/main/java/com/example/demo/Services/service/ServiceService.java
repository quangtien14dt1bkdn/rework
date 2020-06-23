package com.example.demo.Services.service;


import com.example.demo.models.employee.Employee;
import com.example.demo.models.service.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceService {
    Page<service> getAllService(Pageable pageable);

    service getServiceById(Long id);

    void saveService(service Service);

    void deleteServiceById(Long id);
    Page<service> findByNameContaining(String fullName, Pageable pageable);
}
