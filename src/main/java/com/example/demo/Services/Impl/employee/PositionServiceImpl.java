package com.example.demo.Services.Impl.employee;

import com.example.demo.Repository.employee.PositionRepository;
import com.example.demo.Services.employee.PositionService;
import com.example.demo.models.employee.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionRepository positionRepository;

    @Override
    public Page<Position> getAllPosition(Pageable pageable) {
        return positionRepository.findAll(pageable) ;
    }

    @Override
    public Position getPositionById(Long id) {
        return positionRepository.findById(id).orElse(null);
    }

    @Override
    public void savePosition(Position Position) {
        positionRepository.save(Position);
    }

    @Override
    public void deleteById(Long id) {
        positionRepository.deleteById(id);
    }
}
