package com.example.demo.Services.service;


import com.example.demo.models.service.typeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeServiceService {
    Page<typeService> getAllTypeService(Pageable pageable);

    typeService getTypeServiceById(Long id);

    void saveTypeService(typeService TypeService);

    void deleteTypeServiceById(Long id);
}
