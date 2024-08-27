package eni.tp.app.eni_app.bo;

public class Movie {

    public long id;
    public String title;
    //temporaire => Plus tard les notes
    public int note = 4;
    public int year;
    public int duration;
    public String synopsis;
    public String urlImage;

    public long getId() {
        return id;
    }
    public void setId(long id) {
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

    public Movie(long id, String title, int year, int duration, String synopsis, String urlImage) {
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
