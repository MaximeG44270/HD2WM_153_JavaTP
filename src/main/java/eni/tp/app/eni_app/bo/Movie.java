package eni.tp.app.eni_app.bo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Movie {

    public int id;

    @NotBlank(message = "merci de rensigner un titre !!!")
    public String title;

    public int note ;

    @Min(value = 1985, message = "Veuillez saisir année cohérante")
    public int year;

    @Min(value = 1, message = "Il faut au moins 1 min")
    public int duration;

    @NotBlank(message = "Le synospsis est obligatoire")
    public String synopsis;
    public String urlImage;

    public Movie() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Movie(int id, String title, int year, int duration, String synopsis, String urlImage) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.urlImage = urlImage;
    }

    public int getNote() {
        //plus tard quand on va supprimer le int note
        //La note sera la moyenne des avis
        return note;
    }
}
