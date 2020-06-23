package com.example.demo.controller;

import com.example.demo.Services.employee.PartService;
import com.example.demo.models.employee.Part;
import com.example.demo.models.employee.Position;
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
public class PartController {
    @Autowired
    private PartService partService;
    
    @GetMapping("/listPart")
    public ModelAndView listPart(Pageable pageable) {
        Page<Part> parts = partService.getAllPart(pageable);
        return new ModelAndView("admin/part/listPart", "listPart", parts);
    }
    
    @GetMapping("/createPart")
    public ModelAndView showFormCreate() {
        return new ModelAndView("admin/part/createPart", "PartGO", new Part());
    }

    @PostMapping("/createPart")
    public ModelAndView savePart(@ModelAttribute("PartGO") Part Part,Pageable pageable) {
        partService.savePart(Part);
        Page<Part> Parts = partService.getAllPart(pageable);
        ModelAndView modelAndView = new ModelAndView("admin/part/listPart");
        modelAndView.addObject("message", "New Part created successfully");
        modelAndView.addObject("listPart", Parts);
        return modelAndView;
    }
    @GetMapping("/editPart/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Part part = partService.getPartById(id);
        if (part != null) {
            return new ModelAndView("admin/part/editPart", "part", part);
        }
        return new ModelAndView("admin/error");
    }
    @PostMapping("/editPart")
    public ModelAndView deletePart(@ModelAttribute("part") Part part) {
        partService.savePart(part);
        Page<Part> parts = partService.getAllPart(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/part/listPart");
        modelAndView.addObject("message", "Part updated successfully");
        modelAndView.addObject("listPart", parts);
        return modelAndView;
    }
    @GetMapping("/deletePart/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        Part part = partService.getPartById(id);
        if (part != null) {
            return new ModelAndView("admin/part/deletePart", "part", part);
        }
        return new ModelAndView("admin/error");
    }
    @PostMapping("/deletePart")
    public String deletePart(@ModelAttribute("part")Part part, RedirectAttributes redirectAttributes) {
        partService.deleteById(part.getId());
//        redirectAttributes.addFlashAttribute("message","Position deleted successfully");
        return "redirect:listPart";
    }
    @GetMapping("/deleteAllPart")
    public String deleteAllPart(RedirectAttributes redirectAttributes) {
        for (Part part : partService.getAllPart(Pageable.unpaged())) {
            partService.deleteById(part.getId());
        }
        return "redirect:listPart";
    }
}
