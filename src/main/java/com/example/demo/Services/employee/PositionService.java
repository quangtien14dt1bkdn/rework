package com.example.demo.Services.employee;

import com.example.demo.models.employee.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PositionService {
    Page<Position> getAllPosition(Pageable pageable);

    Position getPositionById(Long id);

    void savePosition(Position Position);

    void deleteById(Long id);
}
