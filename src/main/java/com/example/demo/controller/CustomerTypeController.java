package com.example.demo.controller;

import com.example.demo.Services.customer.TypeCustomerService;
import com.example.demo.models.customer.TypeCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
//@RequestMapping("/admin")
public class CustomerTypeController {
    @Autowired
    private TypeCustomerService typeCustomerService;

    @GetMapping("/listCustomerType")
    public ModelAndView listCustomerType() {
        Page<TypeCustomer> typeCustomers = typeCustomerService.getAllTypeCustomer(Pageable.unpaged());
        return new ModelAndView("admin/typeCustomer/listCustomerType", "typePages", typeCustomers);
    }

    @GetMapping("/createCustomerType")
    public ModelAndView newTypeCustomer() {
        return new ModelAndView("admin/typeCustomer/createCustomerType", "newType", new TypeCustomer());
    }

    @PostMapping("/createCustomerType")
    public ModelAndView saveNewTypeCustomer(@ModelAttribute("newType") TypeCustomer typeCustomer) {
        typeCustomerService.saveTypeCustomer(typeCustomer);
        Page<TypeCustomer> customerPage = typeCustomerService.getAllTypeCustomer(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/typeCustomer/listCustomerType");
        modelAndView.addObject("message", "New Type Customer created successfully");
        modelAndView.addObject("typePages", customerPage);
        return modelAndView;
    }

    @GetMapping("/editCustomerType/{id}")
    public ModelAndView editTypeCustomer(@PathVariable("id") Long id) {
        TypeCustomer typeCustomer = typeCustomerService.findTypeCustomerById(id);
        if (typeCustomer != null) {
            return new ModelAndView("admin/typeCustomer/editCustomerType", "customerType", typeCustomer);
        }
        return new ModelAndView("error");
    }

    @PostMapping("/editCustomerType")
    public ModelAndView showListTypeCustomer(@ModelAttribute("customerType") TypeCustomer typeCustomer) {
        typeCustomerService.saveTypeCustomer(typeCustomer);
        Page<TypeCustomer> listTypeCustomer = typeCustomerService.getAllTypeCustomer(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/typeCustomer/listCustomerType");
        modelAndView.addObject("message", "Success");
        modelAndView.addObject("typePages", listTypeCustomer);
        return modelAndView;
    }

    @GetMapping("/deleteCustomerType/{id}")
    public ModelAndView showFormDeleteTypeCustomer(@PathVariable("id") Long id) {
        TypeCustomer typeCustomer = typeCustomerService.findTypeCustomerById(id);
        if (typeCustomer != null) {
            return new ModelAndView("admin/typeCustomer/deleteCustomerType", "TypeCustomer", typeCustomer);
        }
        return new ModelAndView("error");
    }

    @PostMapping("/deleteTypeCustomer")
    public String deleteTypeCustomer(@ModelAttribute("TypeCustomer") TypeCustomer typeCustomer, RedirectAttributes redirectAttributes) {
        typeCustomerService.deleteTypeCustomerById(typeCustomer.getId());
        redirectAttributes.addFlashAttribute("message", "CustomerType deleted successfully");
        return "redirect:listCustomerType";
    }

    @GetMapping("/deleteAllCustomerTypes")
    public String deleteAllTypeCustomer(RedirectAttributes redirectAttributes) {
        for (TypeCustomer typeCustomer : typeCustomerService.getAllTypeCustomer(Pageable.unpaged())) {
            typeCustomerService.deleteTypeCustomerById(typeCustomer.getId());
        }
        redirectAttributes.addFlashAttribute("message", "successfully");
        return "redirect:listCustomerType";
    }
}
