package eni.tp.app.eni_app.bll;

import eni.tp.app.eni_app.bo.Category;
import eni.tp.app.eni_app.dao.IDAOCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements ICategoryManager {

    private IDAOCategory categoryDAO;

    public CategoryManager(IDAOCategory categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> getCategories(){
        return categoryDAO.findAllCategory();
    }

    @Override
    public Category getCategory(Long id){
        return categoryDAO.findById(id);
    }
}