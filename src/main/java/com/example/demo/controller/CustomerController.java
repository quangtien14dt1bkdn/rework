package com.example.demo.controller;

import com.example.demo.Services.CustomerService;
import com.example.demo.Services.TypeCustomerService;
import com.example.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin")
public class CustomerController {
    @Autowired
    TypeCustomerService typeCustomerService;

    @Autowired
    CustomerService customerService;


    @GetMapping("/listCustomer")
    public ModelAndView getListCustomer(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/listCustomer");
        modelAndView.addObject("listCustomer", customerService.getAllCustomer(pageable));
        return modelAndView;
    }

    @GetMapping("/addNewCustomer")
    public ModelAndView getNewCustomer() {
        return new ModelAndView("addCustomer", "customerAdd", new Customer());
    }

//    @PostMapping("/addNewCustomer")
//    public ModelAndView saveCustomer(@Validated @ModelAttribute("customerAdd") Customer customer, BindingResult bindingResult, Pageable pageable) {
//        ModelAndView modelAndView;
//        if (bindingResult.hasFieldErrors()) {
//            modelAndView = new ModelAndView("addCustomer");
//            modelAndView.addObject("message", "New Customer created not successfully");
//        } else {
//            customerService.saveCustomer(customer);
//            Page<Customer> customers = customerService.getAllCustomer();
//            modelAndView = new ModelAndView("admin/customer/listCustomer");
//            modelAndView.addObject("listCustomer", customers);
//        }
//        return modelAndView;
//    }


}
