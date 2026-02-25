package com.adrar.cdafad.service;

import com.adrar.cdafad.entity.Category;
import com.adrar.cdafad.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    //Méthodes du service
    //Méthode qui ajoute une category
    public Category createCategory(Category category) throws Exception {
        try {
            this.isCategoryExistsByName(category.getName());
        } catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
        return categoryRepository.save(category);
    }

    //Méthode qui retourne une category par son ID
    public Category getCategoryById(Integer id) throws Exception {
        //Récupération de la category (findById du repository)
        Optional<Category> category = categoryRepository.findById(id);
        //Test si elle n'existe pas
        if (!category.isPresent()) {
            throw new Exception("La category n'existe pas");
        }
        return category.get();
    }

    //méthode qui retourne la liste de toutes les categories
    public Iterable<Category> getAllCategories() throws Exception {
        //Test si la list est vide
        if (categoryRepository.count() == 0) {
            throw new Exception("La liste des categories est vide");
        }
        return categoryRepository.findAll();
    }

    //méthode pour retourner une category par son name
    private boolean isCategoryExistsByName(String name) throws Exception {
        //Test si la category existe
        if(categoryRepository.existsByName(name)) {
            throw new Exception("La category " + name + " existe déja");
        }
        return true;
    }

    public void deleteCategoryById(Integer id) throws Exception
    {
        try {
            this.categoryRepository.deleteById(id);
        } catch (Exception e)
        {
            throw new Exception("Suppression impossible");
        }
    }
}
