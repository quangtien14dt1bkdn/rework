package com.example.demo.controller;

import com.example.demo.Services.service.ServiceService;
import com.example.demo.Services.service.TypeRentService;
import com.example.demo.Services.service.TypeServiceService;
import com.example.demo.models.employee.Employee;
import com.example.demo.models.service.TypeRent;
import com.example.demo.models.service.service;
import com.example.demo.models.service.typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private TypeRentService typeRentService;
    @Autowired
    private TypeServiceService typeServiceService;

    @ModelAttribute("typeRent")
    public Page<TypeRent> typeRents() {
        return typeRentService.getAllTypeRent(Pageable.unpaged());
    }

    @ModelAttribute("typeService")
    public Page<typeService> typeServices() {
        return typeServiceService.getAllTypeService(Pageable.unpaged());
    }

    @GetMapping("/listService")
    public ModelAndView listService(@RequestParam("s") Optional<String> search, @PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("admin/service/listService");
        Page<service> services;
        if (search.isPresent()) {
            services = serviceService.findByNameContaining(search.get(), pageable);
            modelAndView.addObject("s", search.get());
        } else {
            services = serviceService.getAllService(pageable);
        }
        modelAndView.addObject("listService", services);
        return modelAndView;
    }

    @GetMapping("/createService")
    public ModelAndView showFormCreate() {
        return new ModelAndView("admin/service/createService", "service", new service());
    }

    @PostMapping("/createService")
    public ModelAndView saveService(@Validated @ModelAttribute("service") service service, BindingResult bindingResult,@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView;
//        if (bindingResult.hasFieldErrors()) {
//            modelAndView = new ModelAndView("/employee/createEmployee");
//            modelAndView.addObject("message", "New Employee created not successfully");
//        } else {
        serviceService.saveService(service);
        Page<service> services = serviceService.getAllService(pageable);
        modelAndView = new ModelAndView("admin/service/listService");
//        modelAndView.addObject("message", "New Employee created successfully");
        modelAndView.addObject("listService", services);
//        }
        return modelAndView;
    }




    @GetMapping("/editService/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        service service = serviceService.getServiceById(id);
        if (service != null) {
            return new ModelAndView("admin/service/editService", "service", service);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/editService")
    public ModelAndView updateService(@ModelAttribute("service") service service,@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView;
        serviceService.saveService(service);
        Page<service> services = serviceService.getAllService(pageable);
        modelAndView = new ModelAndView("admin/service/listService");
        modelAndView.addObject("listService", services);
        return modelAndView;
    }

    @GetMapping("/deleteService/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        service service = serviceService.getServiceById(id);
        if (service != null) {
            return new ModelAndView("admin/service/deleteService", "service", service);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/deleteService")
    public String deleteService(@ModelAttribute("service") service service, RedirectAttributes redirectAttributes) {
        serviceService.deleteServiceById(service.getId());
        return "redirect:listService";
    }

    @GetMapping("/viewService/{id}")
    public ModelAndView viewService(@PathVariable("id") Long id) {
        service service = serviceService.getServiceById(id);
        if (service != null) {
            return new ModelAndView("admin/service/viewService", "service", service);
        }
        return new ModelAndView("admin/error");
    }
    @PostMapping("/viewService")
    public String returnView() {
        return "redirect:listService";
    }
    @GetMapping("/deleteAllService")
    public String deleteAllService(RedirectAttributes redirectAttributes, Pageable pageable) {
        for (service service : serviceService.getAllService(pageable)) {
            serviceService.deleteServiceById(service.getId());
        }
        return "redirect:listService";
    }
}


