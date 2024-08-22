package eni.tp.app.eni_app.DAO;

import eni.tp.app.eni_app.bo.Movie;

import java.util.List;

public interface IDAOMovie {

    List<Movie> selectMovies();

    Movie selectMovieById(long id);

}
