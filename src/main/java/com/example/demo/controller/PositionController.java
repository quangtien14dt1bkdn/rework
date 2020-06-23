package com.example.demo.controller;

import com.example.demo.Services.employee.PositionService;
import com.example.demo.models.employee.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/listPosition")
    public ModelAndView listPosition() {
        Page<Position> positions = positionService.getAllPosition(Pageable.unpaged());
        return new ModelAndView("admin/position/listPosition", "listPosition", positions);
    }

    @GetMapping("/createPosition")
    public ModelAndView showFormCreate() {
        return new ModelAndView("admin/position/createPosition", "positionGO", new Position());
    }

    @PostMapping("/createPosition")
    public ModelAndView savePosition(@ModelAttribute("positionGO") Position position) {
        positionService.savePosition(position);
        Page<Position> positions = positionService.getAllPosition(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/position/listPosition");
        modelAndView.addObject("message", "New Position created successfully");
        modelAndView.addObject("listPosition", positions);
        return modelAndView;
    }

    @GetMapping("/editPosition/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Position position = positionService.getPositionById(id);
        if (position != null) {
            return new ModelAndView("admin/position/editPosition", "positionSend", position);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/editPosition")
    public ModelAndView deletePosition(@ModelAttribute("positionSend") Position position) {
        positionService.savePosition(position);
        Page<Position> positions = positionService.getAllPosition(Pageable.unpaged());
        ModelAndView modelAndView = new ModelAndView("admin/position/listPosition");
        modelAndView.addObject("message", "Position updated successfully");
        modelAndView.addObject("listPosition", positions);
        return modelAndView;
    }

    @GetMapping("/deletePosition/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        Position position = positionService.getPositionById(id);
        if (position != null) {
            return new ModelAndView("admin/position/deletePosition", "position", position);
        }
        return new ModelAndView("admin/error");
    }

    @PostMapping("/deletePosition")
    public String deletePosition(@ModelAttribute("position")Position position, RedirectAttributes redirectAttributes) {
        positionService.deleteById(position.getId());
//        redirectAttributes.addFlashAttribute("message","Position deleted successfully");
        return "redirect:listPosition";
    }

    @GetMapping("/deleteAllPosition")
    public String deleteAllPosition(RedirectAttributes redirectAttributes) {
        for (Position position : positionService.getAllPosition(Pageable.unpaged())) {
            positionService.deleteById(position.getId());
        }
        return "redirect:listPosition";
    }
}
