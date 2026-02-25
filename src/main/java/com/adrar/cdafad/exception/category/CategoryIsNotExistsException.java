package com.adrar.cdafad.exception.category;

public class CategoryIsNotExistsException extends RuntimeException {
    public CategoryIsNotExistsException(Integer id) {
        super("La category avec id : " + id + " n'existe pas");
    }
}
