package br.com.fiap.iottu.motorcycle;

import br.com.fiap.iottu.motorcyclestatus.MotorcycleStatusService;
import br.com.fiap.iottu.yard.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/motorcycles")
public class MotorcycleController {

    @Autowired
    private MotorcycleService service;

    @Autowired
    private YardService yardService;

    @Autowired
    private MotorcycleStatusService statusService;

    private void addFormData(Model model) {
        model.addAttribute("yards", yardService.findAll());
        model.addAttribute("statuses", statusService.findAll());
    }

    @GetMapping
    public String listMotorcycles(Model model) {
        model.addAttribute("motorcycles", service.findAll());
        return "motorcycle/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("motorcycle", new Motorcycle());
        addFormData(model);
        return "motorcycle/form";
    }

    @PostMapping
    public String saveMotorcycle(@ModelAttribute Motorcycle motorcycle) {
        service.save(motorcycle);
        return "redirect:/motorcycles";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("motorcycle", service.findById(id).orElseThrow());
        addFormData(model);
        return "motorcycle/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMotorcycle(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/motorcycles";
    }

}
