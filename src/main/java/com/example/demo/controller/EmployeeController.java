package com.example.demo.controller;

import com.example.demo.Services.employee.EmployeeService;
import com.example.demo.Services.employee.LevelService;
import com.example.demo.Services.employee.PartService;
import com.example.demo.Services.employee.PositionService;
import com.example.demo.models.employee.Employee;
import com.example.demo.models.employee.Level;
import com.example.demo.models.employee.Part;
import com.example.demo.models.employee.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private LevelService levelService;
    @Autowired
    private PartService partService;
    @Autowired
    private PositionService positionService;

    @ModelAttribute("position")
    public Page<Position> positions() {
        return positionService.getAllPosition(Pageable.unpaged());
    }

    @ModelAttribute("Level")
    public Page<Level> levels() {
        return levelService.getAllLevel(Pageable.unpaged());
    }

    @ModelAttribute("Part")
    public Page<Part> parts() {
        return partService.getAllPart(Pageable.unpaged());
    }


    @GetMapping("/listEmployee")
    public ModelAndView listEmployee(@RequestParam("s") Optional<String> s, @PageableDefault(value = 5) Pageable pageable) {
        Page<Employee> employees;
        if (s.isPresent()) {
            employees = employeeService.findByNameContaining(s.get(), pageable);
        } else {
            employees = employeeService.getAllEmployee(pageable);
        }
        return new ModelAndView("admin/employee/listEmployee", "listEmployee", employees);
    }

    @GetMapping("/createEmployee")
    public ModelAndView showFormCreate() {
        return new ModelAndView("admin/employee/createEmployee", "employee", new Employee());
    }

    @PostMapping("/createEmployee")
    public ModelAndView saveEmployee(@Validated @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Pageable pageable) {
        ModelAndView modelAndView;
//        if (bindingResult.hasFieldErrors()) {
//            modelAndView = new ModelAndView("/employee/createEmployee");
//            modelAndView.addObject("message", "New Employee created not successfully");
//        } else {
        employeeService.saveEmployee(employee);
        Page<Employee> employees = employeeService.getAllEmployee(pageable);
        modelAndView = new ModelAndView("admin/employee/listEmployee");
        modelAndView.addObject("message", "New Employee created successfully");
        modelAndView.addObject("listEmployee", employees);
//        }
        return modelAndView;
    }

    @GetMapping("/editEmployee/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return new ModelAndView("admin/employee/editEmployee", "employee", employee);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/editEmployee")
    public ModelAndView updateEmployee(@ModelAttribute("employee") Employee employee,Pageable pageable) {
        ModelAndView modelAndView;
        employeeService.saveEmployee(employee);
        Page<Employee> employees = employeeService.getAllEmployee(pageable);
        modelAndView = new ModelAndView("admin/employee/listEmployee");
        modelAndView.addObject("listEmployee", employees);
        return modelAndView;
    }

    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return new ModelAndView("admin/employee/deleteEmployee", "employee", employee);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        employeeService.deleteEmployeeById(employee.getId());
        return "redirect:listEmployee";
    }

    @GetMapping("/deleteAllEmployee")
    public String deleteAllEmployee(RedirectAttributes redirectAttributes, Pageable pageable) {
        for (Employee employee : employeeService.getAllEmployee(pageable)) {
            employeeService.deleteEmployeeById(employee.getId());
        }
        return "redirect:listEmployee";
    }

    @GetMapping("/viewEmployee/{id}")
    public ModelAndView viewEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return new ModelAndView("admin/employee/viewEmployee", "employee", employee);

        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/viewEmployee")
    public String returnViewEmployee() {
        return "redirect:listEmployee";
    }
}
