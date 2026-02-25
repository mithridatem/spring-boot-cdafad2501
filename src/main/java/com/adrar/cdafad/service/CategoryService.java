package com.adrar.cdafad.service;

import com.adrar.cdafad.entity.Category;
import com.adrar.cdafad.exception.category.CategoryIsNotExistsException;
import com.adrar.cdafad.exception.category.CategoryIsPresentException;
import com.adrar.cdafad.exception.category.CategoryListIsEmptyException;
import com.adrar.cdafad.exception.category.DeleteCategoryImpossibleException;
import com.adrar.cdafad.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    //Méthodes du service
    //Méthode qui ajoute une category
    public Category createCategory(Category category)  {

        if(categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryIsPresentException(category.getName());
        }

        return categoryRepository.save(category);
    }

    //Méthode qui retourne une category par son ID
    public Category getCategoryById(Integer id){
        //Récupération de la category (findById du repository)
        Optional<Category> category = categoryRepository.findById(id);
        //Test si elle n'existe pas
        if (!category.isPresent()) {
            throw new CategoryIsNotExistsException(id);
        }
        return category.get();
    }

    //méthode qui retourne la liste de toutes les categories
    public Iterable<Category> getAllCategories()  {
        //Test si la list est vide
        if (categoryRepository.count() == 0) {
            throw new CategoryListIsEmptyException();
        }
        return categoryRepository.findAll();
    }


    public void deleteCategoryById(Integer id)
    {
        try {
            this.categoryRepository.deleteById(id);
        } catch (Exception e)
        {
            throw new DeleteCategoryImpossibleException();
        }
    }
}
