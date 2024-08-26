package eni.tp.app.eni_app.DAO;

//import eni.demo.demo.module4.Aliment;
import eni.tp.app.eni_app.bo.Movie;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//@Profile("Mock")
@Component
public class DAOMovieMock implements IDAOMovie {


        List<Movie> movies = Arrays.asList(
        new Movie(1, "Moi moche et méchant 4", 2024, 94, "hello, synopsis", "image/moi-moche-et-mechant-4.jpeg"),
        new Movie(2, "Civil War", 2024, 109, "hello, synopsis", "image/civil-war-picture.jpeg"),
        new Movie(3, "Avengers", 2012, 143, "hello, synopsis", "image/avenger-picture.jpg" ),
        new Movie(4,"le comte de monteCristo", 2024, 178, "hello, synopsis", "image/le-comte-de-montecristo.jpg"),
        new Movie(5, "La momie", 2017, 111, "hello, synopsis", "image/la-momie.jpg"),
        new Movie(6, "La planète des singes - Le nouveau royaume", 2024, 145, "hello, synopsis", "image/planete-des-signes-nouveau-royaume.jpg"),
        new Movie(7, "The Wave", 2016, 110, "hello synopsis", "image/the-wave.jpg")
        );



    @Override
    public List<Movie> selectMovies() {
       return movies;
    }

    @Override
    public Movie selectMovieById(long id) {
        Movie movieToFound = movies.stream()
                .filter(movie -> movie.id == id)
                .findFirst()
                .orElse(null);
        return movieToFound;
    }
}