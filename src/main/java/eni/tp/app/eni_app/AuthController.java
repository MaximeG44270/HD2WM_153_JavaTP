package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            return "auth/already-logged-page";
        }

        // Instancier un User vide (email et password vide)
        Member user = new Member();

        // Envoyer le user dans le Model
        model.addAttribute("user", user);

        // Afficher la page qui contient le formulaire
        return "auth/login-page";
    }

    @PostMapping("login")
    public String processLogin(@ModelAttribute Member user, Model model, RedirectAttributes redirectAttributes) {
        //mettre l'user dans la session
        model.addAttribute("loggedUser", user);

        //Ajouter un message temporaire(flash message)
        redirectAttributes.addFlashAttribute("flashMessage", "Vous êtes connecté");

        //rediriger sur la page d'accueil
        return "redirect:/Home";
    }

    @GetMapping("logout")
    public String logout(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        sessionStatus.setComplete();

        redirectAttributes.addFlashAttribute("flashMessage", "Vous êtes déconnecté");

        //rediriger à la page d'acceuil
        return "redirect:/Home";
    }
}