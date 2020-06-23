package com.example.demo.Services.employee;

import com.example.demo.models.employee.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartService {
    Page<Part> getAllPart(Pageable pageable);

    Part getPartById(Long id);

    void savePart(Part Part);

    void deleteById(Long id);
}
