package com.example.demo.Services.Impl.employee;

import com.example.demo.Repository.employee.LevelRepository;
import com.example.demo.Services.employee.LevelService;
import com.example.demo.models.employee.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    LevelRepository levelRepository;

    @Override
    public Page<Level> getAllLevel(Pageable pageable) {
        return levelRepository.findAll(pageable);
    }

    @Override
    public Level getLevelById(Long id) {
        return levelRepository.findById(id).orElse(null);
    }

    @Override
    public void saveLevel(Level level) {
        levelRepository.save(level);
    }

    @Override
    public void deleteLevelById(Long id) {
        levelRepository.deleteById(id);
    }
}
