package eni.tp.app.eni_app.dao;


import eni.tp.app.eni_app.bo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Profile("MySQL")
@Component
public class DAOMovieMySQL implements IDAOMovie {

    @Autowired
    JdbcTemplate jdbcTemplate;

    static final RowMapper<Movie> MOVIE_ROW_MAPPER = new RowMapper<Movie>() {
        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie movie = new Movie();

            movie.id = rs.getInt("id");
            movie.title = rs.getString("title");
            movie.note = rs.getInt("note");
            movie.year = rs.getInt("year");
            movie.duration = rs.getInt("duration");
            movie.synopsis = rs.getString("synopsis");
            movie.urlImage = rs.getString("urlImage");

            return movie;
        }
    };

    @Override
    public List<Movie> selectMovies() {

        return jdbcTemplate.query("SELECT * FROM movie", MOVIE_ROW_MAPPER);
    }


    @Override
    public Movie selectMovieById(int id) {
        List<Movie>movies = jdbcTemplate.query("SELECT * FROM movie WHERE id = ?", MOVIE_ROW_MAPPER, id);

        if(movies.size() == 0) {
            return null;
        }
        return movies.get(0);
    }

    @Override
    public void save(Movie movie) {
        if (movie.getId()!= 0 && selectMovieById(movie.getId()) != null) {
            jdbcTemplate.update("UPDATE movie SET title = ?, year = ?, duration = ?, synopsis = ? WHERE id = ?",
                    movie.title, movie.year, movie.duration, movie.synopsis, movie.id);
            return;
        }
        jdbcTemplate.update("INSERT INTO movie (title, note, year, duration, synopsis) VALUES (?, ?, ?, ?, ?)",
                movie.title, 3, movie.year, movie.duration, movie.synopsis);
    }
}
