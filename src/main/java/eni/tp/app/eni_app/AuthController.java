package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bo.Member;
import eni.tp.app.eni_app.ihm.FlashMessage;
import eni.tp.app.eni_app.ihm.ihmHelpers;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//cela veus dire que le controller reste en session
@SessionAttributes({"loggedUser"})
@Controller
public class AuthController {

    @GetMapping("login")
    public String showLogin(Model model) {

        //Tester si déjà connecté
       Member loggedUser = (Member) model.getAttribute("loggedUser");

        if (loggedUser != null) {
            return "auth/profile";
        }

        // Instancier un User vide (email et password vide)
        Member user = new Member();

        // Envoyer le user dans le Model
        model.addAttribute("user", user);

        // Afficher la page qui contient le formulaire
        return "auth/login-page";
    }

    @PostMapping("login")
    public String processLogin(@Valid @ModelAttribute Member user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        /*
        if (bindingResult.hasErrors()) {
        }*/

        //mettre l'user dans la session
        model.addAttribute("loggedUser", user);

        //Ajouter un message temporaire(flash message)
        redirectAttributes.addFlashAttribute("flashMessage",
                new FlashMessage(FlashMessage.TYPE_FLASH_SUCCESS, "You have successfully logged in."));

        ihmHelpers.sendCommonFlashMessage(redirectAttributes, FlashMessage.TYPE_FLASH_SUCCESS, "You have logged in successfully.");

        //rediriger sur la page d'accueil
        return "redirect:/Home";
    }

    @GetMapping("logout")
    public String logout(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        sessionStatus.setComplete();

        redirectAttributes.addFlashAttribute("flashMessage", "You have successfully logged in.");

        ihmHelpers.sendCommonFlashMessage(redirectAttributes, FlashMessage.TYPE_FLASH_SUCCESS, "You have logged in successfully.");

        //rediriger à la page d'acceuil
        return "redirect:/Home";
    }

    @GetMapping("Register")
    public String Register() {
        return "auth/register";
    }
}