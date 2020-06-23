package com.example.demo.Repository.employee;

import com.example.demo.models.employee.Level;
import com.example.demo.models.employee.Position;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PositionRepository extends PagingAndSortingRepository<Position, Long> {

}
