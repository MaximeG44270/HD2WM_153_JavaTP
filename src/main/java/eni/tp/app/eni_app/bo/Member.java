package eni.tp.app.eni_app.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Member {

    // @Pattern() pour faire les regex
    @Email(message = "Veuillez rentrer une adresse mail valide !!")
    @NotBlank( message = "L'adresse mail doit être renseigné")
    public String email;

    @NotBlank( message = "Le mot de passe doit être renseigné")
    @Size(min=2, max=250, message = "Doit avoir au moins 2 caractères")
    public String password;

    public int id;
    public String prenom;
    public String nom;

    public Member(String email, String password) {
        this.email = "";
        this.password = "";
        this.id = id;
        this.prenom = "";
        this.nom = "";
    }

    public Member() {

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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}