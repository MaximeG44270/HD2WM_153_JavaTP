package eni.tp.app.eni_app.ihm.Converter;

import eni.tp.app.eni_app.bll.ICategoryManager;
import eni.tp.app.eni_app.bo.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {
    private ICategoryManager categoryManager;

    public StringToCategoryConverter(ICategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    @Override
    public Category convert(String source) {
        System.out.println("Conversion idCategory : " + source);
        return categoryManager.getCategory(Long.parseLong(source));
    }
}
