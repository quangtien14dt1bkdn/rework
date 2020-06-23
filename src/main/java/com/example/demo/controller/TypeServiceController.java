package com.example.demo.controller;

import com.example.demo.Services.service.TypeServiceService;
import com.example.demo.models.service.TypeRent;
import com.example.demo.models.service.typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TypeServiceController {
    @Autowired
    private TypeServiceService typeServiceService;

    @GetMapping("/listTypeService")
    public ModelAndView listTypeService(Pageable pageable) {
        Page<typeService> typeServices= typeServiceService.getAllTypeService(pageable);
        return new ModelAndView("admin/typeService/listTypeService", "listTypeService", typeServices);
    }
    @GetMapping("/createTypeService")
    public ModelAndView showFormCreate() {
        return new ModelAndView("admin/typeService/createTypeService", "typeService", new typeService());
    }
    @PostMapping("/createTypeService")
    public ModelAndView saveTypeService(@ModelAttribute("typeService") typeService typeService) {
        typeServiceService.saveTypeService(typeService);
        Page<typeService> typeServices = typeServiceService.getAllTypeService(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/typeService/listTypeService");
        modelAndView.addObject("listTypeService", typeServices);
        return modelAndView;
    }
    @GetMapping("/editTypeService/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        typeService typeService = typeServiceService.getTypeServiceById(id);
        if (typeService != null) {
            return new ModelAndView("admin/typeService/editTypeService", "typeService", typeService);
        }
        return new ModelAndView("admin/error");
    }
    @PostMapping("/editTypeService")
    public ModelAndView editTypeService(@ModelAttribute("typeService") typeService typeService) {
        typeServiceService.saveTypeService(typeService);
        Page<typeService> typeServices = typeServiceService.getAllTypeService(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/typeService/listTypeService");
        modelAndView.addObject("listTypeService", typeServices);
        return modelAndView;
    }
    @GetMapping("/deleteTypeService/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        typeService typeService = typeServiceService.getTypeServiceById(id);
        if (typeService != null) {
            return new ModelAndView("admin/typeService/deleteTypeService", "typeService", typeService);
        }
        return new ModelAndView("admin/error");
    }
    @PostMapping("/deleteTypeService")
    public String editTypeService(@ModelAttribute("typeService")typeService typeService, RedirectAttributes redirectAttributes) {
        typeServiceService.deleteTypeServiceById(typeService.getId());
//        redirectAttributes.addFlashAttribute("message","Position deleted successfully");
        return "redirect:listTypeService";
    }
    @GetMapping("deleteAllTypeService")
    public String deleteAllTypeService(RedirectAttributes redirectAttributes) {
        for (typeService typeService : typeServiceService.getAllTypeService(Pageable.unpaged())) {
            typeServiceService.deleteTypeServiceById(typeService.getId());
        }
        return "redirect:listTypeService";
    }
}
