package com.adrar.cdafad.exception.users;

public class UsersIsPresentException extends RuntimeException {
    public UsersIsPresentException() {
        super("L'utilisateur existe déjà");
    }
}
