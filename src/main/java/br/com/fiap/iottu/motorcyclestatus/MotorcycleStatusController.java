package br.com.fiap.iottu.motorcyclestatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/motorcycle-status")
public class MotorcycleStatusController {

    @Autowired
    private MotorcycleStatusService service;

    @GetMapping
    public String listStatus(Model model) {
        model.addAttribute("statuses", service.findAll());
        return "motorcycle-status/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("status", new MotorcycleStatus());
        return "motorcycle-status/form";
    }

    @PostMapping
    public String saveStatus(@ModelAttribute MotorcycleStatus status) {
        service.save(status);
        return "redirect:/motorcycle-status";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("status", service.findById(id).orElseThrow());
        return "motorcycle-status/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteStatus(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/motorcycle-status";
    }

}
