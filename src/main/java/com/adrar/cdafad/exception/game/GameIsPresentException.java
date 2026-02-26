package com.adrar.cdafad.exception.game;

public class GameIsPresentException extends RuntimeException {
    public GameIsPresentException(String title) {
        super("Le game avec le title : " + title + " existe déja");
    }
}
