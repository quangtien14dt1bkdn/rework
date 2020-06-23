package com.example.demo.Services.service;

import com.example.demo.models.service.TypeRent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeRentService {
    Page<TypeRent> getAllTypeRent(Pageable pageable);

    TypeRent getTypeRentById(Long id);

    void saveTypeRent(TypeRent TypeRent);

    void deleteTypeRentById(Long id);
}
