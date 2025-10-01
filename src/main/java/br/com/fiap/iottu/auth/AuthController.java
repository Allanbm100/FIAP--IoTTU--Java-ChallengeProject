package br.com.fiap.iottu.auth;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(Authentication authentication, Model model, @RequestParam(value = "logout", required = false) String logout) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }

        if (logout != null) {
            model.addAttribute("successMessage", "Você foi desconectado com sucesso.");
        }

        return "login";
    }

}
