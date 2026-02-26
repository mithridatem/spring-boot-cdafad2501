package com.adrar.cdafad.exception.game;

public class GameListIsEmptyException extends RuntimeException {
    public GameListIsEmptyException() {
        super("La liste des Game est vide");
    }
}
