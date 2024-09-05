package eni.tp.app.eni_app.bll;

import eni.tp.app.eni_app.bo.Category;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface ICategoryManager {
    List<Category> getCategories();
    Category getCategory(Long id);
}