package com.adrar.cdafad.controller;

import com.adrar.cdafad.entity.Category;
import com.adrar.cdafad.repository.CategoryRepository;
import com.adrar.cdafad.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test()
    {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("code", "200");
        response.put("body", "test");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) throws Exception {
        Category newCategory = this.categoryService.createCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id) throws Exception
    {
        Category category = this.categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<Iterable<Category>> getAllCategories() throws Exception
    {
        Iterable<Category> categories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Map<String, String>> removeCategoryById(@PathVariable Integer id) throws Exception
    {
        this.categoryService.deleteCategoryById(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("info", "La category à été supprimé");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
