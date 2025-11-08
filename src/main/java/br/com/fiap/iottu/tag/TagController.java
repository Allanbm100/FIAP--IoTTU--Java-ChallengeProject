package br.com.fiap.iottu.tag;

import br.com.fiap.iottu.helper.MessageHelper;
import br.com.fiap.iottu.user.User;
import br.com.fiap.iottu.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService service;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageHelper messageHelper;

    @GetMapping
    public String listTags(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null && userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();
            model.addAttribute("tags", service.findOrphanAndUserTags(user.getId()));
        } else {
            model.addAttribute("tags", service.findAll());
        }
        return "tag/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "tag/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Tag tag, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tag/form";
        }
        try {
            service.validateDuplicate(tag);
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("rfidCode", "DuplicateTag", messageHelper.getMessage("message.error.tag.duplicate"));
            return "tag/form";
        }
        service.save(tag);
        redirectAttributes.addFlashAttribute("successMessage", messageHelper.getMessage("message.success.tag.created"));
        return "redirect:/tags";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("tag", service.findById(id).orElseThrow());
        return "tag/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute Tag tag, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tag/form";
        }
        tag.setId(id);
        try {
            service.validateDuplicate(tag);
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("rfidCode", "DuplicateTag", messageHelper.getMessage("message.error.tag.duplicate"));
            return "tag/form";
        }
        service.save(tag);
        redirectAttributes.addFlashAttribute("successMessage", messageHelper.getMessage("message.success.tag.updated"));
        return "redirect:/tags";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            service.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", messageHelper.getMessage("message.success.tag.deleted"));
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("failureMessage", messageHelper.getMessage("message.error.tag.deleteHasMotorcycles"));
        }
        return "redirect:/tags";
    }
}