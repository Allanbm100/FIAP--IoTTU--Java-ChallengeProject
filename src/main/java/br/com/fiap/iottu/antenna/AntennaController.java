package br.com.fiap.iottu.antenna;

import br.com.fiap.iottu.yard.YardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String create(@Valid @ModelAttribute Antenna antenna, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("yards", yardService.findAll());
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao cadastrar antena. Verifique os campos.");
            return "antenna/form";
        }
        service.save(antenna);
        redirectAttributes.addFlashAttribute("successMessage", "Antena cadastrada com sucesso!");
        return "redirect:/antennas";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("antenna", service.findById(id).orElseThrow());
        model.addAttribute("yards", yardService.findAll());
        return "antenna/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute Antenna antenna, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("yards", yardService.findAll());
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao atualizar antena. Verifique os campos.");
            return "antenna/form";
        }
        antenna.setId(id);
        service.save(antenna);
        redirectAttributes.addFlashAttribute("successMessage", "Antena atualizada com sucesso!");
        return "redirect:/antennas";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Antena excluída com sucesso!");
        return "redirect:/antennas";
    }
}
