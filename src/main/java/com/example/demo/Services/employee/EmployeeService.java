package com.example.demo.Services.employee;

import com.example.demo.models.customer.Customer;
import com.example.demo.models.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<Employee> getAllEmployee(Pageable pageable);

    Employee getEmployeeById(Long id);

    void saveEmployee(Employee Employee);

    void deleteEmployeeById(Long id);

    Page<Employee> findByNameContaining(String fullName, Pageable pageable);
}
