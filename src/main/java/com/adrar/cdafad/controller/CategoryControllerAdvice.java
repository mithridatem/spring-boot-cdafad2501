package com.adrar.cdafad.controller;

import com.adrar.cdafad.exception.category.CategoryIsNotExistsException;
import com.adrar.cdafad.exception.category.CategoryIsPresentException;
import com.adrar.cdafad.exception.category.CategoryListIsEmptyException;
import com.adrar.cdafad.exception.category.DeleteCategoryImpossibleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CategoryControllerAdvice {

    @ExceptionHandler(CategoryIsPresentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> CategoryIsExists(CategoryIsPresentException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }

    @ExceptionHandler(CategoryIsNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> CategoryIsNotExists(CategoryIsNotExistsException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }

    @ExceptionHandler(CategoryListIsEmptyException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String, String> CategoryListIsEmpty(CategoryListIsEmptyException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }

    @ExceptionHandler(DeleteCategoryImpossibleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> DeleteCategoryImpossible(DeleteCategoryImpossibleException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }
}
