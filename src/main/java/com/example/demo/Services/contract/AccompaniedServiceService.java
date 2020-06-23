package com.example.demo.Services.contract;


import com.example.demo.models.contractWar.AccompaniedService;
import com.example.demo.models.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccompaniedServiceService {
    Page<AccompaniedService> getAllAccompaniedService(Pageable pageable);

    AccompaniedService getAccompaniedServiceById(Long id);

    void saveAccompaniedService(AccompaniedService AccompaniedService);

    void deleteAccompaniedServiceById(Long id);
    Page<AccompaniedService> findByNameContaining(String fullName, Pageable pageable);
}
