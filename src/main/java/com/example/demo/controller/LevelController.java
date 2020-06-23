package com.example.demo.controller;

import com.example.demo.Services.employee.LevelService;
import com.example.demo.models.employee.Level;
import com.example.demo.models.employee.Position;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
public class LevelController {
    @Autowired
    private LevelService levelService;

    @GetMapping("/listLevel")
    public ModelAndView listLevel() {
        Page<Level> levels = levelService.getAllLevel(Pageable.unpaged());
        return new ModelAndView("admin/level/listLevel", "listLevel", levels);
    }
    @GetMapping("/createLevel")
    public ModelAndView showFormCreate(){
        return new ModelAndView("admin/level/createLevel", "level", new Level());
    }

    @PostMapping("/createLevel")
    public ModelAndView saveLevel(@ModelAttribute("level") Level level) {
        levelService.saveLevel(level);
        Page<Level> levels = levelService.getAllLevel(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/level/listLevel");
        modelAndView.addObject("message", "New Level created successfully");
        modelAndView.addObject("listLevel", levels);
        return modelAndView;
    }

    @GetMapping("/editLevel/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Level level = levelService.getLevelById(id);
        if (level != null) {
            return new ModelAndView("admin/level/editLevel", "level", level);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/editLevel")
    public ModelAndView deleteLevel(@ModelAttribute("level") Level level) {
        levelService.saveLevel(level);
        Page<Level> levels = levelService.getAllLevel(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/level/listLevel");
        modelAndView.addObject("message", "Level updated successfully");
        modelAndView.addObject("listLevel", levels);
        return modelAndView;
    }
    @GetMapping("/deleteLevel/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        Level level = levelService.getLevelById(id);
        if (level != null) {
            return new ModelAndView("admin/level/deleteLevel", "level",level);
        }
        return new ModelAndView("admin/error");
    }
    @PostMapping("/deleteLevel")
    public String deletePosition(@ModelAttribute("level")Level level, RedirectAttributes redirectAttributes) {
        levelService.deleteLevelById(level.getId());
//        redirectAttributes.addFlashAttribute("message","Position deleted successfully");
        return "redirect:listLevel";
    }
    @GetMapping("/deleteAllLevel")
    public String deleteAllLevel(RedirectAttributes redirectAttributes) {
        for (Level level : levelService.getAllLevel(Pageable.unpaged())) {
            levelService.deleteLevelById(level.getId());
        }
        return "redirect:listLevel";
    }
}
