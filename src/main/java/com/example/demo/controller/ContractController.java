package com.example.demo.controller;

import com.example.demo.Services.contract.AccompaniedServiceService;
import com.example.demo.Services.contract.ContractDetailsService;
import com.example.demo.Services.contract.ContractService;
import com.example.demo.Services.customer.CustomerService;
import com.example.demo.Services.employee.EmployeeService;
import com.example.demo.Services.service.ServiceService;
import com.example.demo.models.contractWar.AccompaniedService;
import com.example.demo.models.contractWar.Contract;
import com.example.demo.models.contractWar.ContractDetails;
import com.example.demo.models.customer.Customer;
import com.example.demo.models.employee.Employee;
import com.example.demo.models.service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContractController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ContractService contractService;


    @ModelAttribute("service")
    public Page<service> services() {
        return serviceService.getAllService(Pageable.unpaged());
    }

    @ModelAttribute("customer")
    public Page<Customer> customers() {
        return customerService.getAllCustomer(Pageable.unpaged());
    }

    @ModelAttribute("employee")
    public Page<Employee> employees() {
        return employeeService.getAllEmployee(Pageable.unpaged());
    }

    @GetMapping("/listContract")
    public ModelAndView listContract(@PageableDefault(value = 5) Pageable pageable) {
        Page<Contract> contracts;
        contracts = contractService.getAllContract(pageable);
        return new ModelAndView("admin/contract/listContract", "listContract", contracts);
    }

    @GetMapping("/createContract")
    public ModelAndView showFormCreate() {
        return new ModelAndView("admin/contract/createContract", "contract", new Contract());
    }

    @PostMapping("/createContract")
    public ModelAndView saveContract(@Validated @ModelAttribute("contract") Contract contract, BindingResult bindingResult, Pageable pageable) {
        ModelAndView modelAndView;
        contractService.saveContract(contract);
        Page<Contract> contracts = contractService.getAllContract(pageable);
        modelAndView = new ModelAndView("admin/contract/listContract");
        modelAndView.addObject("listContract", contracts);
        return modelAndView;
    }

    @GetMapping("/editContract/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Contract contract = contractService.getContractById(id);
        if (contract != null) {
            return new ModelAndView("admin/contract/editContract", "contract", contract);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/editContract")
    public ModelAndView updateContract(@ModelAttribute("contract") Contract contract, Pageable pageable) {
        ModelAndView modelAndView;
        contractService.saveContract(contract);
        Page<Contract> contracts = contractService.getAllContract(pageable);
        modelAndView = new ModelAndView("admin/contract/listContract");
        modelAndView.addObject("listContract", contracts);
        return modelAndView;
    }

    @GetMapping("/deleteContract/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        Contract contract = contractService.getContractById(id);
        if (contract != null) {
            return new ModelAndView("admin/contract/deleteContract", "contract", contract);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/deleteContract")
    public String deleteContract(@ModelAttribute("contract") Contract contract, RedirectAttributes redirectAttributes) {
        contractService.deleteContractById(contract.getId());
        return "redirect:listContract";
    }

    @GetMapping("/deleteAllContract")
    public String deleteAllContract(RedirectAttributes redirectAttributes) {
        for (Contract contract : contractService.getAllContract(Pageable.unpaged())) {
            contractService.deleteContractById(contract.getId());
        }
        return "redirect:listContract";
    }

    @GetMapping("/viewContract/{id}")
    public ModelAndView viewContract(@PathVariable("id") Long id) {
        Contract contract = contractService.getContractById(id);
        if (contract != null) {
            return new ModelAndView("admin/contract/viewContract", "contract", contract);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("viewContract")
    public String returnViewContract() {
        return "redirect:listContract";
    }
}
