package com.adrar.cdafad.exception.users;

public class UsersIsNotExistsException extends RuntimeException {

    public UsersIsNotExistsException() {
        super("L'utilisateur n'existe pas");
    }
}
