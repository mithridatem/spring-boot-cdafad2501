package com.adrar.cdafad.exception.category;

public class CategoryListIsEmptyException extends RuntimeException {
    public CategoryListIsEmptyException() {
        super("La liste des categories est vide");
    }
}
