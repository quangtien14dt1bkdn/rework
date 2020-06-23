package com.example.demo.Services.Impl.employee;

import com.example.demo.Repository.employee.PartRepository;
import com.example.demo.Services.employee.PartService;
import com.example.demo.models.employee.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {
    @Autowired
    PartRepository partRepository;

    @Override
    public Page<Part> getAllPart(Pageable pageable) {
        return partRepository.findAll(pageable);
    }

    @Override
    public Part getPartById(Long id) {
        return partRepository.findById(id).orElse(null);
    }

    @Override
    public void savePart(Part Part) {
        partRepository.save(Part);
    }

    @Override
    public void deleteById(Long id) {
        partRepository.deleteById(id);
    }
}
