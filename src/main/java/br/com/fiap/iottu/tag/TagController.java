package br.com.fiap.iottu.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService service;

    @GetMapping
    public String listTags(Model model) {
        model.addAttribute("tags", service.findAll());
        return "tag/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "tag/form";
    }

    @PostMapping
    public String saveTag(@ModelAttribute Tag tag) {
        service.save(tag);
        return "redirect:/tags";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("tag", service.findById(id).orElseThrow());
        return "tag/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTag(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/tags";
    }

}
