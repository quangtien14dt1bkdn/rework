package com.example.demo.Services.Impl.service;


import com.example.demo.Repository.service.TypeServiceRepository;
import com.example.demo.Services.service.TypeServiceService;
import com.example.demo.models.service.typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceServiceImpl implements TypeServiceService {
    @Autowired
    TypeServiceRepository typeServiceRepository;

    @Override
    public Page<typeService> getAllTypeService(Pageable pageable) {
        return typeServiceRepository.findAll(pageable);
    }

    @Override
    public typeService getTypeServiceById(Long id) {
        return typeServiceRepository.findById(id).orElse(null) ;
    }

    @Override
    public void saveTypeService(typeService TypeService) {
        typeServiceRepository.save(TypeService);
    }

    @Override
    public void deleteTypeServiceById(Long id) {
        typeServiceRepository.deleteById(id);
    }
}
