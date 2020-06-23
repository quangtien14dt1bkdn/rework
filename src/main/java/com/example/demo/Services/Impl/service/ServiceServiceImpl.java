package com.example.demo.Services.Impl.service;

import com.example.demo.Repository.service.ServiceRepository;
import com.example.demo.Services.service.ServiceService;
import com.example.demo.models.service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public Page<service> getAllService(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public service getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public void saveService(service Service) {
        serviceRepository.save(Service);
    }

    @Override
    public void deleteServiceById(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Page<service> findByNameContaining(String fullName, Pageable pageable) {
        return serviceRepository.findByNameContaining(fullName, pageable);
    }

}

