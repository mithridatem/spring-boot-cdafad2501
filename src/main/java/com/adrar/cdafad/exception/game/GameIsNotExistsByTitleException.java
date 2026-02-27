package com.adrar.cdafad.exception.game;

public class GameIsNotExistsByTitleException extends RuntimeException {
    public GameIsNotExistsByTitleException(String title) {
        super("le jeux avec : " + title + " n'existe pas !");
    }
}
