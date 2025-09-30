package br.com.fiap.iottu.antenna;

import br.com.fiap.iottu.yard.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/antennas")
public class AntennaController {

    @Autowired
    private AntennaService service;

    @Autowired
    private YardService yardService;

    @GetMapping
    public String listAntennas(Model model) {
        model.addAttribute("antennas", service.findAll());
        return "antenna/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("antenna", new Antenna());
        model.addAttribute("yards", yardService.findAll());
        return "antenna/form";
    }

    @PostMapping
    public String create(@ModelAttribute Antenna antenna) {
        service.save(antenna);
        return "redirect:/antennas";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("antenna", service.findById(id).orElseThrow());
        model.addAttribute("yards", yardService.findAll());
        return "antenna/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Antenna antenna) {
        antenna.setId(id);
        service.save(antenna);
        return "redirect:/antennas";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/antennas";
    }
}
