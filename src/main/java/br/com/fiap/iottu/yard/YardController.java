package br.com.fiap.iottu.yard;

import br.com.fiap.iottu.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/yards")
public class YardController {

    @Autowired
    private YardService service;

    @Autowired
    private UserService userService;

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
    public String saveYard(@ModelAttribute Yard yard) {
        service.save(yard);
        return "redirect:/yards";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("yard", service.findById(id).orElseThrow());
        model.addAttribute("users", userService.findAll());
        return "yard/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteYard(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/yards";
    }

}
