package com.example.demo.Services.Impl;

import com.example.demo.Repository.TypeCustomerRepository;
import com.example.demo.Services.TypeCustomerService;
import com.example.demo.models.TypeCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TypeCustomerImpl implements TypeCustomerService {
    @Autowired
    TypeCustomerRepository typeCustomerRepository;

    @Override
    public Page<TypeCustomer> getAllTypeCustomer(Pageable pageable) {
        return typeCustomerRepository.findAll(pageable);
    }

    @Override
    public TypeCustomer findTypeCustomerById(Long id) {
        return typeCustomerRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTypeCustomer(TypeCustomer typeCustomer) {
        typeCustomerRepository.save(typeCustomer);
    }

    @Override
    public void deleteTypeCustomerById(Long id) {
        typeCustomerRepository.deleteById(id);
    }
}
