package br.com.fiap.iottu.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", service.findAll());
        return "user/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("user", service.findById(id).orElseThrow());
        return "user/profile";
    }

    @GetMapping("/new")
    public String formForCreate(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao cadastrar usuário. Verifique os campos.");
            return "user/form";
        }
        service.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "Usuário cadastrado com sucesso!");
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String formForUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("user", service.findById(id).orElseThrow());
        return "user/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao atualizar usuário. Verifique os campos.");
            return "user/form";
        }
        user.setId(id);
        service.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "Usuário atualizado com sucesso!");
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Usuário excluído com sucesso!");
        return "redirect:/users";
    }
}
