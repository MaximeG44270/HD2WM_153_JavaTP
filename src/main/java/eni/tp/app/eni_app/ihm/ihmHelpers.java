package eni.tp.app.eni_app.ihm;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ihmHelpers {
    public static  void sendCommonFlashMessage(RedirectAttributes redirectAttributes, int type, String message) {
        redirectAttributes.addFlashAttribute("flashMessage",
                new FlashMessage(type, message));
    }
}
