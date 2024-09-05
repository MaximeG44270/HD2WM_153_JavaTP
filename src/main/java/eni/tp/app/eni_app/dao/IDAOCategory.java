package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.Category;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface IDAOCategory {
    List<Category> findAllCategory();
    Category findById(Long id);
}