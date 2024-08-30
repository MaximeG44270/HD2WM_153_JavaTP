package eni.tp.app.eni_app.bll;

import eni.tp.app.eni_app.dao.IDAOMovie;
import eni.tp.app.eni_app.bo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieManager {

    @Autowired
    IDAOMovie daoMovie;
    /**
     * Le manager qui récupère la liste des Movies
     * @return
     *
     */
    public List<Movie> getAll() {
        //Récupère les movies de la DAO
        List<Movie> movies = daoMovie.selectMovies();
        return movies;

    }
    public Movie getById(long id) {
        Movie movie = daoMovie.selectMovieById((int) id);
        return movie;
    }

    public void save(Movie movie) {
        daoMovie.save(movie);
    }
}
