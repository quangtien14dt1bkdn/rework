package com.example.demo.controller;

import com.example.demo.Services.service.TypeRentService;
import com.example.demo.models.employee.Position;
import com.example.demo.models.service.TypeRent;
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
public class TypeRentController {
    @Autowired
    private TypeRentService typeRentService;

    @GetMapping("/listTypeRent")
    public ModelAndView listTypeRent(Pageable pageable) {
        Page<TypeRent> typeRents = typeRentService.getAllTypeRent(pageable);
        return new ModelAndView("admin/typeRent/listTypeRent", "listTypeRent", typeRents);
    }

    @GetMapping("/createTypeRent")
    public ModelAndView showFormCreate() {
        return new ModelAndView("admin/typeRent/createTypeRent", "typeRent", new TypeRent());
    }

    @PostMapping("/createTypeRent")
    public ModelAndView saveTypeRent(@ModelAttribute("typeRent") TypeRent typeRent) {
        typeRentService.saveTypeRent(typeRent);
        Page<TypeRent> typeRents = typeRentService.getAllTypeRent(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/typeRent/listTypeRent");
        modelAndView.addObject("listTypeRent", typeRents);
        return modelAndView;
    }

    @GetMapping("/editTypeRent/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        TypeRent typeRent = typeRentService.getTypeRentById(id);
        if (typeRent != null) {
            return new ModelAndView("admin/typeRent/editTypeRent", "typeRent", typeRent);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/editTypeRent")
    public ModelAndView editTypeRent(@ModelAttribute("typeRent") TypeRent typeRent) {
        typeRentService.saveTypeRent(typeRent);
        Page<TypeRent> typeRents = typeRentService.getAllTypeRent(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/typeRent/listTypeRent");
        modelAndView.addObject("listTypeRent", typeRents);
        return modelAndView;
    }
    @GetMapping("/deleteTypeRent/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
       TypeRent typeRent = typeRentService.getTypeRentById(id);
        if (typeRent != null) {
            return new ModelAndView("admin/typeRent/deleteTypeRent", "typeRent", typeRent);
        }
        return new ModelAndView("admin/error");
    }
    @PostMapping("/deleteTypeRent")
    public String editPosition(@ModelAttribute("typeRent")TypeRent typeRent, RedirectAttributes redirectAttributes) {
        typeRentService.deleteTypeRentById(typeRent.getId());
//        redirectAttributes.addFlashAttribute("message","Position deleted successfully");
        return "redirect:listTypeRent";
    }

    @GetMapping("deleteAllTypeRent")
    public String deleteAllTypeRent(RedirectAttributes redirectAttributes) {
        for (TypeRent typeRent : typeRentService.getAllTypeRent(Pageable.unpaged())) {
            typeRentService.deleteTypeRentById(typeRent.getId());
        }
        return "redirect:listTypeRent";
    }
}
