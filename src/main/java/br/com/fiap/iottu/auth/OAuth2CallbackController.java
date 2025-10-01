package br.com.fiap.iottu.auth;

import br.com.fiap.iottu.user.User;
import br.com.fiap.iottu.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class OAuth2CallbackController {

    @Autowired
    private UserService userService;

    @GetMapping("/oauth2/callback")
    public String oauth2Callback(@AuthenticationPrincipal OAuth2User oauth2User, RedirectAttributes redirectAttributes) {
        Map.Entry<User, Boolean> registrationResult = userService.registerOAuth2User(oauth2User);
        User registeredUser = registrationResult.getKey();
        Boolean isNewUser = registrationResult.getValue();

        if (isNewUser) {
            redirectAttributes.addFlashAttribute("successMessage", "Login realizado com sucesso! Por favor, complete seu cadastro de pátio.");
            return "redirect:/yards/new?oauth2UserEmail=" + registeredUser.getEmail();
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Login realizado com sucesso!");
            return "redirect:/";
        }
    }
}
