package com.example.demo.controller;

import com.example.demo.Services.contract.AccompaniedServiceService;
import com.example.demo.models.contractWar.AccompaniedService;
import com.example.demo.models.employee.Employee;
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
public class AccServiceController {
    @Autowired
    private AccompaniedServiceService accompaniedServiceService;

    @GetMapping("/listAcc")
    public ModelAndView listAcc(@RequestParam("s") Optional<String> s, @PageableDefault(value = 5) Pageable pageable) {
        Page<AccompaniedService> Accs;
        if (s.isPresent()) {
            Accs = accompaniedServiceService.findByNameContaining(s.get(), pageable);
        } else {
            Accs = accompaniedServiceService.getAllAccompaniedService(pageable);
        }
        return new ModelAndView("admin/acc/listAcc", "accs", Accs);
    }

    @GetMapping("/createAcc")
    public ModelAndView showFormCreate() {
        return new ModelAndView("admin/acc/createAcc", "acc", new AccompaniedService());
    }

    @PostMapping("/createAcc")
    public ModelAndView saveAcc(@Validated @ModelAttribute("acc") AccompaniedService accompaniedService, BindingResult bindingResult, Pageable pageable) {
        ModelAndView modelAndView;
        accompaniedServiceService.saveAccompaniedService(accompaniedService);
        Page<AccompaniedService> accompaniedServices = accompaniedServiceService.getAllAccompaniedService(pageable);
        modelAndView = new ModelAndView("admin/acc/listAcc");
        modelAndView.addObject("accs", accompaniedServices);
        return modelAndView;
    }

    @GetMapping("/editAcc/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        AccompaniedService accompaniedService = accompaniedServiceService.getAccompaniedServiceById(id);
        if (accompaniedService != null) {
            return new ModelAndView("admin/acc/editAcc", "acc", accompaniedService);
        }
        return new ModelAndView("admin/error");
    }
    @PostMapping("/editAcc")
    public ModelAndView updateAcc(@ModelAttribute("acc") AccompaniedService accompaniedService, Pageable pageable) {
        ModelAndView modelAndView;
        accompaniedServiceService.saveAccompaniedService(accompaniedService);
        Page<AccompaniedService> accompaniedServices = accompaniedServiceService.getAllAccompaniedService(pageable);
        modelAndView = new ModelAndView("admin/acc/listAcc");
        modelAndView.addObject("accs", accompaniedServices);
        return modelAndView;
    }
    @GetMapping("/deleteAcc/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        AccompaniedService accompaniedService = accompaniedServiceService.getAccompaniedServiceById(id);
        if (accompaniedService != null) {
            return new ModelAndView("admin/acc/deleteAcc", "acc", accompaniedService);
        }
        return new ModelAndView("admin/error");
    }
    @PostMapping("/deleteAcc")
    public String deleteAcc(@ModelAttribute("acc") AccompaniedService accompaniedService, RedirectAttributes redirectAttributes) {
        accompaniedServiceService.deleteAccompaniedServiceById(accompaniedService.getId());
        return "redirect:listAcc";
    }
    @GetMapping("/deleteAllAcc")
    public String deleteAllAcc(RedirectAttributes redirectAttributes, Pageable pageable) {
        for (AccompaniedService accompaniedService : accompaniedServiceService.getAllAccompaniedService(pageable)) {
            accompaniedServiceService.deleteAccompaniedServiceById(accompaniedService.getId());
        }
        return "redirect:listAcc";
    }
}
