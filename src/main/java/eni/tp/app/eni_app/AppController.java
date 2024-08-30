package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bll.MovieManager;
import eni.tp.app.eni_app.bo.Movie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@SessionAttributes({"loggedUser"})
@Controller
public class AppController {
    @Autowired
    MovieManager movieManager;

    @Autowired
    LocaleResolver localeResolver;

    @GetMapping("change-lang/{lang}")
    public String changeLang(@PathVariable("lang") String lang, HttpServletRequest request, HttpServletResponse response) {
        Locale locale= Locale.forLanguageTag(lang);
        localeResolver.setLocale(request, response, locale);
        return "redirect:/Home";
    }

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

        return "list-of-movies";
    }
    @GetMapping("movie-detail/{id}")
    public String detailPage(@PathVariable("id")int id, Model model) {
        Movie movie = movieManager.getById(id);
        if(movie == null){
            return "movie-not-found";
        }
        model.addAttribute("movie", movie);

        List<Integer> maxStars = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("maxStars", maxStars);

        return "movie-detail";
    }



    @GetMapping({"add-movie/{id}","add-movie"})
    public String addMovie(@PathVariable(required = false) Integer id, Model model) {
        // Instancier un film par défaut
        Movie movie = new Movie();

        // Si y'a un id, le film on le récupère grace à l'id
        // PS: On écrase le film vide qu'on voulait afficher dans le form
        // Donc on affichera un film existant dans le formulaire
        if (id != null) {
            movie = movieManager.getById(id);
        }

        // Envoyer le film dans le model
        model.addAttribute("movie", movie);

        // Afficher le formulaire
        return "add-movie";
    }

    @PostMapping("add-movie")
    public String postAddMovie(@Valid @ModelAttribute Movie movie, BindingResult bindingResult) {
        /*
        if (bindingResult.hasErrors()) {
        }*/
        movieManager.save(movie);

        return "add-movie";
    }
}

