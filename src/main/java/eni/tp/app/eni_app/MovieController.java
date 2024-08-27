package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bll.MovieManager;
import eni.tp.app.eni_app.bo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;

@SessionAttributes({"loggedUser"})
@Controller
public class MovieController {
    @Autowired
    MovieManager movieManager;

    @GetMapping("Home")
    public String homePage() {
        return "index";
    }
    @GetMapping("ListOfMovies")
    public String listOfMovies(Model model) {
        //Récupérer la liste
        List<Movie> movies = movieManager.getAll();

        //envoyer les films dans le modèle
        model.addAttribute("movies", movies);

        // Envoyer le dernier film ajouté
        if (!movies.isEmpty()) {
            Movie lastMovie = movies.get(movies.size() - 1);
            model.addAttribute("lastMovie", lastMovie);
        }

        //Envoyer la note maximale
        List<Integer> maxStars = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("maxStars", maxStars);

        return "list-of-movies";
    }
    @GetMapping("movie-detail/{id}")
    public String detailPage(@PathVariable("id")long id, Model model) {
        Movie movie = movieManager.getById(id);
        if(movie == null){
            return "movie-not-found";
        }
        model.addAttribute("movie", movie);
        return "movie-detail";
    }
    @GetMapping("Register")
    public String Register() {
        return "auth/register";
    }
}