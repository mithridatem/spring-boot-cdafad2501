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
@Data
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
}
