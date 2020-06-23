package com.example.demo.Services.Impl.employee;

import com.example.demo.Repository.employee.EmployeeRepository;
import com.example.demo.Services.employee.EmployeeService;
import com.example.demo.models.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> getAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEmployee(Employee Employee) {
        employeeRepository.save(Employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findByNameContaining(String fullName, Pageable pageable) {
        return employeeRepository.findByNameContaining(fullName, pageable);
    }
}
