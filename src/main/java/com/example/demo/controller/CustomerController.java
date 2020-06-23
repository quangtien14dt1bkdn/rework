package com.example.demo.controller;

import com.example.demo.Services.customer.CustomerService;
import com.example.demo.Services.customer.TypeCustomerService;
import com.example.demo.models.customer.Customer;
import com.example.demo.models.customer.TypeCustomer;
import javassist.util.HotSwapAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;


import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TypeCustomerService typeCustomerService;

    @ModelAttribute("typeCustomer")
    public Page<TypeCustomer> customers() {
        return typeCustomerService.getAllTypeCustomer(Pageable.unpaged());
    }

    @GetMapping("/listCustomer")
    public ModelAndView listCustomer(@RequestParam("s") Optional<String> s, @PageableDefault(value = 5) Pageable pageable) {
        Page<Customer> customers;
        if (s.isPresent()) {
            customers = customerService.findAll(s.get(), pageable);
        } else {
            customers = customerService.getAllCustomer(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("admin/customer/listCustomer");
        modelAndView.addObject("listCustomer", customers);
        return modelAndView;
//        return new ModelAndView("admin/customer/listCustomer", "listCustomer", customers);
    }

    @GetMapping("/createCustomer")
    public ModelAndView showFormCreate() {
        return new ModelAndView("admin/customer/createCustomer", "customer", new Customer());
    }

    @PostMapping("/createCustomer")
    public ModelAndView saveCustomer(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Pageable pageable) {
        ModelAndView modelAndView;
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("admin/customer/createCustomer");
            modelAndView.addObject("message", "New Customer created not successfully");
        } else {
            customerService.saveCustomer(customer);
            Page<Customer> customers = customerService.getAllCustomer(pageable);
            modelAndView = new ModelAndView("admin/customer/listCustomer");
            modelAndView.addObject("message", "New Employee created not successfully");
            modelAndView.addObject("listCustomer", customers);
        }
        return modelAndView;
    }

    @GetMapping("/editCustomer/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return new ModelAndView("admin/customer/editCustomer", "customer", customer);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/editCustomer")
    public ModelAndView updateCustomer(@Validated @ModelAttribute("customer") Customer customer, Pageable pageable) {
        ModelAndView modelAndView;
        customerService.saveCustomer(customer);
        Page<Customer> customers = customerService.getAllCustomer(pageable);
        modelAndView = new ModelAndView("admin/customer/listCustomer");
        modelAndView.addObject("listCustomer", customers);
        return modelAndView;
    }

    @GetMapping("/deleteCustomer/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return new ModelAndView("admin/customer/deleteCustomer", "customer", customer);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/deleteCustomer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        customerService.deleteCustomerById(customer.getId());
        return "redirect:listCustomer";
    }

    @GetMapping("/viewCustomer/{id}")
    public ModelAndView viewCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return new ModelAndView("admin/customer/viewCustomer", "customer", customer);
        }
        return new ModelAndView("admin/error");
    }
    @PostMapping("/viewCustomer")
    public String returnViewCustomer() {
        return "redirect:listCustomer";
    }
}
