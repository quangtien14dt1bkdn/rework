package com.example.demo.Services.Impl.contract;

import com.example.demo.Repository.contract.AccompaniedServiceRepository;
import com.example.demo.Services.contract.AccompaniedServiceService;
import com.example.demo.models.contractWar.AccompaniedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccompaniedServiceServiceImpl implements AccompaniedServiceService {
    @Autowired
    AccompaniedServiceRepository accompaniedServiceRepository;

    @Override
    public Page<AccompaniedService> getAllAccompaniedService(Pageable pageable) {
        return accompaniedServiceRepository.findAll(pageable);
    }

    @Override
    public AccompaniedService getAccompaniedServiceById(Long id) {
        return accompaniedServiceRepository.findById(id).orElse(null);
    }

    @Override
    public Page<AccompaniedService> findByNameContaining(String fullName, Pageable pageable) {
        return accompaniedServiceRepository.findByNameContaining(fullName, pageable);
    }

    @Override
    public void saveAccompaniedService(AccompaniedService AccompaniedService) {
        accompaniedServiceRepository.save(AccompaniedService);
    }

    @Override
    public void deleteAccompaniedServiceById(Long id) {
        accompaniedServiceRepository.deleteById(id);
    }
}
