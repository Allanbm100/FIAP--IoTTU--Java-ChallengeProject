package br.com.fiap.iottu.yard;

import br.com.fiap.iottu.antenna.Antenna;
import br.com.fiap.iottu.antenna.AntennaService;
import br.com.fiap.iottu.motorcycle.Motorcycle;
import br.com.fiap.iottu.motorcycle.MotorcycleService;
import br.com.fiap.iottu.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/yards")
public class YardController {

    @Autowired
    private YardService service;

    @Autowired
    private UserService userService;

    @Autowired
    private AntennaService antennaService;

    @Autowired
    private MotorcycleService motorcycleService;

    @Autowired
    private YardMapService yardMapService;

    @GetMapping
    public String listYards(Model model) {
        model.addAttribute("yards", service.findAll());
        return "yard/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("yard", new Yard());
        model.addAttribute("users", userService.findAll());
        return "yard/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Yard yard, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao cadastrar pátio. Verifique os campos.");
            return "yard/form";
        }
        service.save(yard);
        redirectAttributes.addFlashAttribute("successMessage", "Pátio cadastrado com sucesso!");
        return "redirect:/yards";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("yard", service.findById(id).orElseThrow());
        model.addAttribute("users", userService.findAll());
        return "yard/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute Yard yard, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao atualizar pátio. Verifique os campos.");
            return "yard/form";
        }
        yard.setId(id);
        service.save(yard);
        redirectAttributes.addFlashAttribute("successMessage", "Pátio atualizado com sucesso!");
        return "redirect:/yards";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pátio excluído com sucesso!");
        return "redirect:/yards";
    }

    @GetMapping("/{id}/map")
    public String showYardMap(@PathVariable Integer id, Model model) {
        Yard yard = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Pátio inválido: " + id));
        List<Antenna> antennas = antennaService.findByYardId(id);
        List<Motorcycle> motorcycles = motorcycleService.findByYardId(id);

        YardMapDTO mapData = yardMapService.createMap(antennas, motorcycles);

        model.addAttribute("yard", yard);
        model.addAttribute("mapData", mapData);

        return "yard/map";
    }
}
