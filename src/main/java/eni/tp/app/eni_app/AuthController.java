package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bll.AuthManager;
import eni.tp.app.eni_app.bll.ManagerResponse;
import eni.tp.app.eni_app.bo.Member;
import eni.tp.app.eni_app.ihm.FlashMessage;
import eni.tp.app.eni_app.ihm.ihmHelpers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    public AuthManager authManager;

    public AuthController(AuthManager authManager) {
        this.authManager = authManager;
    }

    @GetMapping("login")
    public String showLogin() {

//        //Tester si déjà connecté
//       Member loggedUser = (Member) model.getAttribute("loggedUser");
//
//        if (loggedUser != null) {
//            return "auth/profile";
//        }
//
//        // Instancier un User vide (email et password vide)
//        Member user = new Member();
//
//        // Envoyer le user dans le Model
//        model.addAttribute("member", user);

        // Afficher la page qui contient le formulaire
        return "auth/login-page";
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication) {
        return "auth/profile";
    }

    @PostMapping("login")
    public String processLogin(@Valid @ModelAttribute Member member, BindingResult bindingResult,Model model, RedirectAttributes redirectAttributes) {

//        // 1 : Contrôle de surface
//
//        // Erreur : si contrôle de surface
//        if (bindingResult.hasErrors()) {
//            return "auth/login-page";
//        }
//
//        // 2 : Contrôle metier (le manager)
//        // Erreur code 756 retourner la page avec l'erreur métier
//        ManagerResponse<Member> response = authManager.authenticate(member.email, member.password);
//
//        // Erreur 756 retourner la page avec l'erreur métier
//        if (response.code.equals("756")) {
//            //TODO Pendant qu'on retourne la page de connexion(envoyer l'erreur métier)
//        }
//
//        // 3 : Connecter l'user en session
//        //mettre l'user dans la session
//        model.addAttribute("loggedUser", member);
//
//        //Ajouter un message temporaire(flash message)
//        redirectAttributes.addFlashAttribute("flashMessage",
//                new FlashMessage(FlashMessage.TYPE_FLASH_SUCCESS, "You have successfully logged in."));
//
//        ihmHelpers.sendCommonFlashMessage(redirectAttributes, FlashMessage.TYPE_FLASH_SUCCESS, "You have logged in successfully.");

        //rediriger sur la page d'accueil
        return "redirect:/Home";
    }

    @GetMapping("logout")
    public String logout(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        sessionStatus.setComplete();

        redirectAttributes.addFlashAttribute("flashMessage", "You have successfully logged out.");

        ihmHelpers.sendCommonFlashMessage(redirectAttributes, FlashMessage.TYPE_FLASH_ERROR, "You have successfully logged out.");

        //rediriger à la page d'acceuil
        return "redirect:/Home";
    }

    @GetMapping("/register")
    public String Register() {
        return "auth/register";
    }
}