package com.adrar.cdafad.exception.category;

public class DeleteCategoryImpossibleException extends RuntimeException {
    public DeleteCategoryImpossibleException() {
        super("La suppression est impossible");
    }
}
