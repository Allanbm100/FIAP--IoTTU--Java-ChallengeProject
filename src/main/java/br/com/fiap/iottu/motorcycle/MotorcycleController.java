package br.com.fiap.iottu.motorcycle;

import br.com.fiap.iottu.motorcyclestatus.MotorcycleStatusService;
import br.com.fiap.iottu.tag.Tag;
import br.com.fiap.iottu.tag.TagService;
import br.com.fiap.iottu.yard.YardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/motorcycles")
public class MotorcycleController {

    @Autowired
    private MotorcycleService service;

    @Autowired
    private YardService yardService;

    @Autowired
    private MotorcycleStatusService statusService;

    @Autowired
    private TagService tagService;

    private void addFormData(Model model) {
        model.addAttribute("yards", yardService.findAll());
        model.addAttribute("statuses", statusService.findAll());

        List<Tag> availableTags = tagService.findAvailableTags();
        model.addAttribute("availableTags", availableTags);
    }

    private void addFormData(Model model, Motorcycle motorcycle) {
        addFormData(model);

        Tag currentOrSelectedTag = null;

        if (motorcycle.getTags() != null && !motorcycle.getTags().isEmpty()) {
            currentOrSelectedTag = motorcycle.getTags().get(0);
        } else if (motorcycle.getSelectedTagId() != null) {
            currentOrSelectedTag = tagService.findById(motorcycle.getSelectedTagId()).orElse(null);
        }

        if (currentOrSelectedTag != null) {
            List<Tag> tagsForForm = (List<Tag>) model.getAttribute("availableTags");
            if (!tagsForForm.contains(currentOrSelectedTag)) {
                tagsForForm.add(currentOrSelectedTag);
            }
            tagsForForm.sort((t1, t2) -> t1.getRfidCode().compareTo(t2.getRfidCode()));
            model.addAttribute("availableTags", tagsForForm);
        }
    }

    @GetMapping
    public String listMotorcycles(Model model) {
        model.addAttribute("motorcycles", service.findAll());
        return "motorcycle/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Motorcycle newMotorcycle = new Motorcycle();
        model.addAttribute("motorcycle", newMotorcycle);
        addFormData(model);
        return "motorcycle/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Motorcycle motorcycle, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addFormData(model, motorcycle);
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao cadastrar moto. Verifique os campos.");
            return "motorcycle/form";
        }

        Integer selectedTagId = motorcycle.getSelectedTagId();
        Optional<Tag> tagOptional = tagService.findById(selectedTagId);

        if (tagOptional.isEmpty()) {
            bindingResult.rejectValue("selectedTagId", "NotFound", "Tag selecionada não encontrada.");
            addFormData(model, motorcycle);
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao cadastrar moto. Tag não encontrada.");
            return "motorcycle/form";
        }

        Tag selectedTag = tagOptional.get();
        if (selectedTag.getMotorcycles() != null && !selectedTag.getMotorcycles().isEmpty()) {
            bindingResult.rejectValue("selectedTagId", "TagAlreadyAssigned", "A tag selecionada já está associada a outra moto.");
            addFormData(model, motorcycle);
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao cadastrar moto. Tag já em uso.");
            return "motorcycle/form";
        }

        motorcycle.setTags(new ArrayList<>());
        motorcycle.getTags().add(selectedTag);

        service.save(motorcycle);
        redirectAttributes.addFlashAttribute("successMessage", "Moto cadastrada com sucesso!");
        return "redirect:/motorcycles";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Motorcycle motorcycle = service.findById(id).orElseThrow();
        if (motorcycle.getTags() != null && !motorcycle.getTags().isEmpty()) {
            motorcycle.setSelectedTagId(motorcycle.getTags().get(0).getId());
        }
        model.addAttribute("motorcycle", motorcycle);

        addFormData(model, motorcycle);
        return "motorcycle/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute Motorcycle motorcycle, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (motorcycle.getSelectedTagId() == null) {
                Motorcycle originalMotorcycle = service.findById(id).orElseThrow();
                if (originalMotorcycle.getTags() != null && !originalMotorcycle.getTags().isEmpty()) {
                    motorcycle.setSelectedTagId(originalMotorcycle.getTags().get(0).getId());
                }
            }
            addFormData(model, motorcycle);
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao atualizar moto. Verifique os campos.");
            return "motorcycle/form";
        }

        Integer selectedTagId = motorcycle.getSelectedTagId();
        Optional<Tag> tagOptional = tagService.findById(selectedTagId);

        if (tagOptional.isEmpty()) {
            bindingResult.rejectValue("selectedTagId", "NotFound", "Tag selecionada não encontrada.");
            addFormData(model, motorcycle);
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao atualizar moto. Tag não encontrada.");
            return "motorcycle/form";
        }

        Tag newTag = tagOptional.get();
        Motorcycle existingMotorcycle = service.findById(id).orElseThrow();

        if (newTag.getMotorcycles() != null && !newTag.getMotorcycles().isEmpty() && !newTag.getMotorcycles().contains(existingMotorcycle)) {
            bindingResult.rejectValue("selectedTagId", "TagAlreadyAssigned", "A tag selecionada já está associada a outra moto.");
            addFormData(model, motorcycle);
            redirectAttributes.addFlashAttribute("failureMessage", "Erro ao atualizar moto. Tag já em uso.");
            return "motorcycle/form";
        }

        if (existingMotorcycle.getTags() != null && !existingMotorcycle.getTags().isEmpty()) {
            Tag oldTag = existingMotorcycle.getTags().get(0);
            if (!oldTag.equals(newTag)) {
                oldTag.getMotorcycles().remove(existingMotorcycle);
                tagService.save(oldTag);
            }
        }

        motorcycle.setId(id);
        motorcycle.setTags(new ArrayList<>());
        motorcycle.getTags().add(newTag);

        service.save(motorcycle);

        redirectAttributes.addFlashAttribute("successMessage", "Moto atualizada com sucesso!");
        return "redirect:/motorcycles";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Motorcycle motorcycleToDelete = service.findById(id).orElseThrow();

        if (motorcycleToDelete.getTags() != null && !motorcycleToDelete.getTags().isEmpty()) {
            Tag associatedTag = motorcycleToDelete.getTags().get(0);
            associatedTag.getMotorcycles().remove(motorcycleToDelete);
            tagService.save(associatedTag);
        }

        service.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Moto excluída com sucesso!");
        return "redirect:/motorcycles";
    }
}
