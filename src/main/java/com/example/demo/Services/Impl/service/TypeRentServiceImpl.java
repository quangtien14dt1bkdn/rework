package com.example.demo.Services.Impl.service;

import com.example.demo.Repository.service.TypeRentRepository;
import com.example.demo.Services.service.TypeRentService;
import com.example.demo.models.service.TypeRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TypeRentServiceImpl implements TypeRentService {
    @Autowired
    TypeRentRepository typeRentRepository;

    @Override
    public Page<TypeRent> getAllTypeRent(Pageable pageable) {
        return typeRentRepository.findAll(pageable);
    }

    @Override
    public TypeRent getTypeRentById(Long id) {
        return typeRentRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTypeRent(TypeRent TypeRent) {
        typeRentRepository.save(TypeRent);
    }

    @Override
    public void deleteTypeRentById(Long id) {
        typeRentRepository.deleteById(id);
    }
}
