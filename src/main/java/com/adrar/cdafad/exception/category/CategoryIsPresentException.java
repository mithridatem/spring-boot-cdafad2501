package com.adrar.cdafad.exception.category;

public class CategoryIsPresentException extends RuntimeException {
    public CategoryIsPresentException(String category) {
        super("La category avec le name : " + category + " existe déja");
    }
}
