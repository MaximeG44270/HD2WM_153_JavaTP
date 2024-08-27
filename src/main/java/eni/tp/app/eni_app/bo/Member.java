package eni.tp.app.eni_app.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Member {

    @Email(message = "Veuillez rentrer une adresse mail valide !!")
    @NotBlank( message = "L'adresse mail doit être renseigné")
    public String email;

    @NotBlank( message = "Le mot de passe doit être renseigné")
    @Size(min=2, max=250, message = "Doit avoir au moins 2 caractères")
    public String password;

    public Member() {
        email = "";
        password = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}