package com.example.demo.Services.employee;


import com.example.demo.models.employee.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LevelService {
    Page<Level> getAllLevel(Pageable pageable);

    Level getLevelById(Long id);

    void saveLevel(Level level);

    void deleteLevelById(Long id);

}
