package com.adrar.cdafad.exception.game;

public class GameIsNotExistsException extends RuntimeException {
    public GameIsNotExistsException(Integer id) {
        super("Le Game avec l'ID : " + id + " n'existe pas");
    }
}
