package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOCategoryMySQL implements IDAOCategory {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DAOCategoryMySQL(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Category> findAllCategory() {
        String sql = "select * from category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }

    @Override
    public Category findById(Long id) {
        String sql = "select * from category where id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, map, BeanPropertyRowMapper.newInstance(Category.class));
    }
}