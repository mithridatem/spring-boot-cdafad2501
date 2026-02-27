package com.adrar.cdafad.exception.users;

public class UsersListIsEmptyException extends RuntimeException {
    public UsersListIsEmptyException() {
        super("La liste des utilisateurs est vide");
    }
}
